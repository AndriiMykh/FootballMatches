package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
	@Query("select t from Team t where t.name=:name")
	Optional<Team> findByName(@Param("name")String name);
	
}
