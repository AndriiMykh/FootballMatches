package com.example.demo.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;

@Service
@Transactional
public class PersonService {
	
	@Autowired
	private PersonRepository repo;
	
	public List<Person> returnAllPersons(){
		return (List<Person>)repo.findAll();
	}
	
	public Optional<Person> returnPersonById(Long id){
		return repo.findById(id);
	}
	
	public Person savePerson(Person person) {
		return repo.save(person);
	}

	public void deleteById(Long id) {
		repo.deleteById(id);
	}
	
	public Optional<Person> findByEmail(String email){
		return repo.findByEmail(email);
	}
}
