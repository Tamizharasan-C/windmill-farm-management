package com.windmill.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.windmill.entities.Windmill;

public interface WindMillRepo extends CrudRepository<Windmill, String> {
	Optional<Windmill> findById(String Id);
}
