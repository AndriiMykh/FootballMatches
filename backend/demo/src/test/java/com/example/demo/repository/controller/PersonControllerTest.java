package com.example.demo.repository.controller;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.example.demo.DemoApplication;
import com.example.demo.entity.Person;
import com.example.demo.exception.DataNotFoundException;
import com.example.demo.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

	@Autowired
	private MockMvc mockMVC;

	@MockBean
	private PersonService service;

    @Autowired
    private ObjectMapper objectMapper;
	private String request="http://localhost:8080/api/persons/";
	@Test
	void shouldReturnAllValuesOnGetMapping() throws Exception {
		List<Long> personsId= new ArrayList<Long>();
		personsId.add(1L);
		personsId.add(2L);
		personsId.add(3L);
		given(service.returnAllPersons()).willReturn(personList());
		MvcResult result = mockMVC.perform(get(request))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()", is(personList().size())))
				.andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("\"name\":\"Andrii\""));
	}
	
	@Test
	void shouldReturnPersonById() throws Exception {
		long personId = 2L;
		Person person = personList().stream().filter(personInside ->personInside.getId().equals(personId)).collect(DemoApplication.toSingleton());
		given(service.returnPersonById(personId)).willReturn(Optional.of(person));
		mockMVC.perform(get(request+"id/"+personId))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id",is((int)personId)));
	}
	
	@Test
	void shouldResolveBadArgument() throws Exception {
		String badRequest = "someText";
		
		MvcResult result = mockMVC.perform(get(request+"id/"+badRequest))
		.andExpect(status().isBadRequest())
		.andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Bad arguments"));
	}
	
	@Test
	void shouldThrowNotFoundException() throws Exception {
		Long personId = 5L;
		
		MvcResult result =mockMVC.perform(get(request+"id/"+personId))
		 	.andExpect(status().isNotFound())
		 	.andReturn();
		
		assertTrue(result.getResponse().getContentAsString().contains("Not found a user with id:"+personId));
	}
	
	@Test
	void shouldSavePerson() throws JsonProcessingException, Exception {
		Person somePerson = new Person(4L,"Andrii", "682303412@mail.ru", "682303412", "1111");
		mockMVC.perform(post(request)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(somePerson)))
				.andExpect(status().isCreated());
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
