package com.windmill.service;

import com.windmill.entities.Energy;
import com.windmill.entities.Report;

public interface WindmillService {
	public Energy saveEnergy(Energy energy);
	public Report getReport(String windmillId);
}
