package com.windmill.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.windmill.entities.Energy;

public interface EnergyRepo extends CrudRepository<Energy, Long >{
	public Optional<Energy> findById(Long Id);
	
	@Query(value = "SELECT min(electricity_generated) FROM Energy where date >= CURDATE() and windmill_id=:windmill_id", nativeQuery = true)
	public Double findMinEnergy(@Param("windmill_id") String windmill_id);
	
	@Query(value = "SELECT max(electricity_generated) FROM Energy where date >= CURDATE() and windmill_id=:windmill_id", nativeQuery = true)
	public Double findMaxEnergy(@Param("windmill_id") String windmill_id);
	
	@Query(value = "SELECT avg(electricity_generated) FROM Energy where date >= CURDATE() and windmill_id=:windmill_id", nativeQuery = true)
	public Double findAvgEnergy(@Param("windmill_id") String windmill_id);
	
	@Query(value = "SELECT sum(electricity_generated) FROM Energy where date >= CURDATE() and windmill_id=:windmill_id", nativeQuery = true)
	public Double findSumEnergy(@Param("windmill_id") String windmill_id);
}