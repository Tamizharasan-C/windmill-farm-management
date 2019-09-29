package com.windmill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.windmill.entities.Energy;
import com.windmill.entities.Report;
import com.windmill.entities.Windmill;
import com.windmill.repo.EnergyRepo;
import com.windmill.repo.WindMillRepo;
import com.windmill.service.WindmillService;

@RestController
public class WindmillController {
	@Autowired
	WindMillRepo windMillRepo;
	
	@Autowired
	EnergyRepo energyRepo;
	
	@Autowired
	WindmillService windmillService;
	
	/**
	 * Service Used to register a windmill
	 * 
	 * @param windmill
	 * @return
	 */
	@PostMapping(path = "/register", produces = "application/json")
	@ResponseBody
	public Windmill registerWindmill(@Validated @RequestBody Windmill windmill) {
		return windMillRepo.save(windmill);
	}

	/**
	 * Service used to store the energy
	 * 
	 * @param energey
	 * @return
	 */
	@PostMapping(path = "/energy", produces = "application/json")
	@ResponseBody
	public Energy saveEnergy(@RequestBody Energy energey) {
		return windmillService.saveEnergy(energey);
	}
	
	/**
	 * service to get a daily report of windmill
	 * 
	 * @param windmillId
	 * @return
	 */
	@GetMapping(path = "/report/{windmillId}", produces = "application/json")
	@ResponseBody
	public Report getReport(@PathVariable(name = "windmillId") String windmillId) {
		return windmillService.getReport(windmillId);
	}
}
