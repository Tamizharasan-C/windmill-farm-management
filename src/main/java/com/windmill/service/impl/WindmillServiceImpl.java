package com.windmill.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.windmill.entities.Energy;
import com.windmill.entities.Report;
import com.windmill.entities.Windmill;
import com.windmill.exception.ResourceNotFoundException;
import com.windmill.repo.EnergyRepo;
import com.windmill.repo.WindMillRepo;
import com.windmill.service.WindmillService;

@Component
public class WindmillServiceImpl implements WindmillService {

	@Autowired
	WindMillRepo windMillRepo;

	@Autowired
	EnergyRepo energyRepo;

	@Override
	public Energy saveEnergy(Energy energy) {
		Optional<Windmill> windmill = windMillRepo.findById(energy.getWindmillId());
		if (windmill.isPresent()) {
			energy.setDate(new Date());
			return energyRepo.save(energy);
		} else {
			throw new ResourceNotFoundException("Windmill id " + energy.getWindmillId() + " is not registered");
		}
	}

	@Override
	public Report getReport(String windmillId) {
		Double avg = energyRepo.findAvgEnergy(windmillId);
		Double sum = energyRepo.findSumEnergy(windmillId);
		Double min = energyRepo.findMinEnergy(windmillId);
		Double max = energyRepo.findMaxEnergy(windmillId);
		return new Report(avg,min,max,sum);
	}

}