package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Person;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.service.PersonService;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

	@Autowired
	private PersonService service;

	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public List<Person> getAllPersons() {
		return service.returnAllPersons();
	}

	@GetMapping("/id/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Person getPersonById(@PathVariable Long id) {
		return service.returnPersonById(id)
				.orElseThrow(() -> new DataNotFoundException("Not found a user with id:" + id));
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public void savePerson(@RequestBody Person person) {
		service.savePerson(person);
	}

	@PutMapping("/id/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Person> updatePerson(@RequestBody Person person, @PathVariable long id) {
		return service.returnPersonById(id).map(updatedPerson -> {
			updatedPerson.setName(person.getName());
			updatedPerson.setEmail(person.getEmail());
			updatedPerson.setPassword(person.getPassword());
			updatedPerson.setPhoneNumber(person.getPhoneNumber());
			return ResponseEntity.ok(updatedPerson);
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/id/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Person> deletePersonByID(@PathVariable long id) {
		return service.returnPersonById(id).map(person -> {
			service.deleteById(id);
			return ResponseEntity.ok(person);
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}
}
