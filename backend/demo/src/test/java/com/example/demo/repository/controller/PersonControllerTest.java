package com.example.demo.repository.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.example.demo.entity.Person;
import com.example.demo.service.PersonService;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

	@Autowired
	private MockMvc mockMVC;

	@MockBean
	private PersonService service;

	private String request="http://localhost:8080/api/persons/";
	@Test
	void shouldReturnAllValuesOnGetMapping() throws Exception {
		given(service.returnAllPersons()).willReturn(personList());
		mockMVC.perform(get(request))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(personList().size())))
				.andReturn();
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
