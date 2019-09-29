package com.windmill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
	 * 
	 * @param windmill
	 * @return
	 */
	@PostMapping(path = "/register", produces = "application/json")
	@ResponseBody
	public Windmill registerWindmill(@Validated @RequestBody Windmill windmill) {
		return windMillRepo.save(windmill);
	}

	@PostMapping(path = "/energy", produces = "application/json")
	@ResponseBody
	public Energy saveEnergy(@RequestBody Energy energey) {
		return windmillService.saveEnergy(energey);
	}
	
	@PostMapping(path = "/report", produces = "application/json")
	@ResponseBody
	public Report getReport(@RequestBody Energy energey) {
		return windmillService.getReport(energey.getWindmillId());
	}
}
