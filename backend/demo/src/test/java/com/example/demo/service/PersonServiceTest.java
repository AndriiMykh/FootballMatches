package com.example.demo.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.assertj.core.condition.AnyOf;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;

import com.example.demo.DemoApplication;
import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class PersonServiceTest {
	
	@Mock
	PersonRepository repo;

	@InjectMocks
	PersonService service;

	@Test
	void shouldReturnListOfPersons() {
		given(service.returnAllPersons()).willReturn(personList());
		List<Person> allPersons = service.returnAllPersons();
		
		assertThat(allPersons).hasSize(3);
	}
	
	@Test
	void shouldReturnPersonById() {
		Long personId = 1L;
		Person person = personList().stream().filter(streamPerson-> 
		streamPerson.getId()==personId).collect(DemoApplication.toSingleton());
		given(service.returnPersonById(personId)).willReturn(Optional.of(person));
		Optional<Person> searchPerson = service.returnPersonById(personId);
		assertThat(searchPerson.get().getName()).isEqualTo("Andrii");
		
	}
	
	@Test
	void shouldSavePersonSuccesfully() {
		Person firstPerson = new Person(1L,"Andrii", "682303412@mail.ru", "682303412", "1111");
		when(service.savePerson(Mockito.any(Person.class))).thenAnswer(i -> i.getArguments()[0]);
		assertThat(service.savePerson(firstPerson)).isInstanceOf(Person.class);
	}
	
	@Test
	void shouldDeleteItemById() {
		service.deleteById(1L);
		verify(repo, atLeastOnce()).deleteById(1L);
		verify(repo, never()).deleteById(2L);
	}

	
	private List<Person> personList(){
		Person firstPerson = new Person(1L,"Andrii", "682303412@mail.ru", "682303412", "1111");
		Person secondPerson = new Person(2L,"Vasya", "682303434@mail.ru", "682303434", "1111");
		Person thirdPerson = new Person(3L,"Andrii", "682303431@mail.ru", "682303431", "1111");
		List<Person> persons = new ArrayList<Person>();
		persons.add(firstPerson);
		persons.add(secondPerson);
		persons.add(thirdPerson);
		return persons;
		
	}


}
