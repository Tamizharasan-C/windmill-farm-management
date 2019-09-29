package com.windmill;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.windmill.entities.Windmill;
import com.windmill.repo.WindMillRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WindmillFarmManagementApplicationTests {

	@Autowired
	WindMillRepo windMillRepo;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void givenWindmill_whenSaveWindmill_ThenOk(){
		Windmill windmill = new Windmill("1234567890123459", "", "", 123, 16);
		windMillRepo.save(windmill);
		Optional<Windmill> findById = windMillRepo.findById("1234567890123459");
		System.out.println();
	}

}
