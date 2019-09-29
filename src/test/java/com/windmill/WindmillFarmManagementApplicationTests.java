package com.windmill;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.windmill.entities.Energy;
import com.windmill.entities.Report;
import com.windmill.entities.Windmill;
import com.windmill.repo.EnergyRepo;
import com.windmill.repo.WindMillRepo;
import com.windmill.service.WindmillService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WindmillFarmManagementApplicationTests {

	private MockMvc mockMvc;
	
	@Autowired
	WindMillRepo windMillRepo;
	
	@Autowired
	EnergyRepo energyRepo;
	
	@Autowired
	WindmillService windmillService;
	
	@Autowired
    private WebApplicationContext wac;
	
	@Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void givenWindmill_whenSaveWindmill_ThenOk() throws Exception{
		Windmill windmill = new Windmill("1234567890123459", "Test Company", "Test Address", 123.0, 16.0);
		
		mockMvc.perform(
				post("/register")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(windmill)))
		.andExpect(status().isOk());
		
		Optional<Windmill> windmillGenerated = windMillRepo.findById("1234567890123459");
		assertTrue(windmillGenerated.isPresent());
	}
	
	@Test
	public void givenEnergy_whenSaveEnergy_ThenOk() throws Exception{
		Windmill windmill = new Windmill("1234567890123459", "Test Company", "Test Address", 123, 16);
		windMillRepo.save(windmill);
		
		Energy energy = new Energy("1234567890123459", 15.7);
		
		MvcResult mvcResult = mockMvc.perform(
				post("/energy")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new Gson().toJson(energy)))
				.andExpect(status().isOk()).andReturn();
		ObjectMapper mapper = new ObjectMapper();
		Energy energySaved = mapper.readValue(mvcResult.getResponse().getContentAsString(), Energy.class);
		Optional<Energy> eneryRetrieved = energyRepo.findById(energySaved.getId());
		assertTrue(eneryRetrieved.isPresent());
	}
	
	@Test
	public void givenEnergy_whenSaveEnergyANDWindmillNotRegistered_ThenResourceNotFoundException() throws Exception {
		Energy energy = new Energy("1234557890123459", 15.7);
		mockMvc.perform(
			post("/energy")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new Gson().toJson(energy)))
			.andExpect(status().isNotFound()).andReturn();
	}
	
	@Test
	public void validateReport() throws Exception {
		Windmill windmillA = new Windmill("1234567890123456", "Test A Company", "Chennai", 87.12, -16.43);
		Windmill windmillB = new Windmill("1234567890123457", "Test B Company", "Mumbai", 123.01, -45.31);
		
		windMillRepo.deleteAll();
		windMillRepo.save(windmillA);
		windMillRepo.save(windmillB);
		
		Energy energyA1 = new Energy("1234567890123456", 5.0);
		Energy energyA2 = new Energy("1234567890123456", 2.5);
		Energy energyA3 = new Energy("1234567890123456", 3);
		
		Energy energyB1 = new Energy("1234567890123457", 8.0);
		Energy energyB2 = new Energy("1234567890123457", 1.5);
		Energy energyB3 = new Energy("1234567890123457", 10.75);
		
		energyRepo.deleteAll();
		windmillService.saveEnergy(energyA1);
		windmillService.saveEnergy(energyA2);
		windmillService.saveEnergy(energyA3);
		
		windmillService.saveEnergy(energyB1);
		windmillService.saveEnergy(energyB2);
		windmillService.saveEnergy(energyB3);
		
		MvcResult mvcResult = mockMvc.perform(
			get("/report/1234567890123456")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk()).andReturn();
		
		ObjectMapper mapper = new ObjectMapper();
		Report report = mapper.readValue(mvcResult.getResponse().getContentAsString(), Report.class);
		Assert.assertEquals(2.5, report.getMin(), 0.0);
	}
	
}
