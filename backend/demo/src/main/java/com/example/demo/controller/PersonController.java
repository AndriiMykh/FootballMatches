package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Event;
import com.example.demo.entity.Person;
import com.example.demo.exception.AlreadyPresentOnEventListException;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.exception.NoAvailablePlacesException;
import com.example.demo.repository.EventRepository;
import com.example.demo.service.EventService;
import com.example.demo.service.PersonService;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
	@Autowired
	private PersonService personService;

	@Autowired
	private EventService eventService;

	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public List<Person> getAllPersons() {
		return personService.returnAllPersons();
	}

	@GetMapping("/id/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Person getPersonById(@PathVariable Long id) {
		return personService.returnPersonById(id)
				.orElseThrow(() -> new DataNotFoundException("Not found a user with id:" + id));
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public void savePerson(@RequestBody Person person) {
		personService.savePerson(person);
	}

	@PutMapping("/id/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Person> updatePerson(@RequestBody Person person, @PathVariable long id) {
		return personService.returnPersonById(id).map(updatedPerson -> {
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
		return personService.returnPersonById(id).map(person -> {
			personService.deleteById(id);
			return ResponseEntity.ok(person);
		}).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/id/{personId}/signPersonToEvent/event/{eventId}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Event> signPersonToEvent(@PathVariable("personId") Long personId, @PathVariable("eventId") Long eventId) {
		Person person = personService.returnPersonById(personId)
				.orElseThrow(() ->  new DataNotFoundException("Not found a user with id:" + personId));
		Event event = eventService.returnEventById(eventId)
				.orElseThrow(() -> new DataNotFoundException("Not found an event with id:" + eventId));
		if(!person.getEvents().contains(event)&&event.getAvailablePLaces()>0) {
			event.setAvailablePLaces(event.getAvailablePLaces()-1);
			person.getEvents().add(event);
			event.getPersons().add(person);
			personService.savePerson(person);
			eventService.save(event);
			return ResponseEntity.ok(event);
		} else if(event.getAvailablePLaces()<=0){
			throw new NoAvailablePlacesException();
		}
		else
			throw new AlreadyPresentOnEventListException();
		
	}
}
