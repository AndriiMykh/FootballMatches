package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
	@Query("select p from Person p  where p.email=:email")
	Optional<Person> findByEmail(@Param("email") String email);
}
