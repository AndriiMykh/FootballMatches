package com.example.demo.repository.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	public List<Person> getAllPersons(){
		return service.returnAllPersons();
	}
	
	@GetMapping("/id/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Person getPersonById(@PathVariable Long id){
		return service.returnPersonById(id)
				.orElseThrow(()->new DataNotFoundException("Not found a user with id:"+id));
	}
	
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public void savePerson(@RequestBody Person person){
		 service.savePerson(person);
	}
	
	
//	@PostMapping("/")
//	@ResponseStatus(HttpStatus.CREATED)
//	public 
}
