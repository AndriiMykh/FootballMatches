package com.example.demo.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.example.demo.DemoApplication;
import com.example.demo.entity.Event;
import com.example.demo.entity.Person;
import com.example.demo.service.EventService;
import com.example.demo.service.PersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PersonController.class)
class PersonControllerTest {

	@Autowired
	private MockMvc mockMVC;

	@MockBean
	private PersonService personService;
	@MockBean
	private EventService eventService;
	
    @Autowired
    private ObjectMapper objectMapper;
	private String request="http://localhost:8080/api/persons/";
	@Test
	void shouldReturnAllValuesOnGetMapping() throws Exception {
		List<Long> personsId= new ArrayList<Long>();
		personsId.add(1L);
		personsId.add(2L);
		personsId.add(3L);
		given(personService.returnAllPersons()).willReturn(personList());
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
		given(personService.returnPersonById(personId)).willReturn(Optional.of(person));
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
		Person somePerson = new Person("Andrii", "682303412@mail.ru", "682303412", "1111");
		mockMVC.perform(post(request)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(somePerson)))
				.andExpect(status().isCreated());
	}
	
	@Test
	void shouldUpdatePersonById() throws JsonProcessingException, Exception {
		Person updatedPerson = new Person(1L,"Katya", "213432432123@mail.ru", "213432432123", "1111");
		Person oldperson=personList().stream().filter(person->person.getId().equals(updatedPerson.getId())).collect(DemoApplication.toSingleton());
		
		given(personService.returnPersonById(updatedPerson.getId())).willReturn(Optional.of(oldperson));
		
		mockMVC.perform(put(request+"id/"+updatedPerson.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatedPerson)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name",is(updatedPerson.getName())))
				.andExpect(jsonPath("$.email",is(updatedPerson.getEmail())))
				.andExpect(jsonPath("$.phoneNumber",is(updatedPerson.getPhoneNumber())));
	}
	
	@Test
	void shouldThrowDataNotFound() throws Exception {
		long id =50L;
		given(personService.returnPersonById(id)).willReturn(Optional.empty());
		Person updatedPerson = new Person(1L,"Katya", "213432432123@mail.ru", "213432432123", "1111");
		mockMVC.perform(put(request+"id/"+id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatedPerson)))
				.andExpect(status().isNotFound());
	}
	
	@Test
	void shouldUseDeleteMethod() throws Exception {
		long id =50L;
		Person deletedPerson = new Person(50L,"Katya", "213432432123@mail.ru", "213432432123", "1111");
        
		given(personService.returnPersonById(id)).willReturn(Optional.of(deletedPerson));
        doNothing().when(personService).deleteById(id);
		
        mockMVC.perform(delete(request+"id/"+id))
        	.andExpect(status().isOk());
	}
	
	@Test
	void shouldReturnWhenDelitingNonExistingPerson() throws Exception {
		long id =50L;
		given(personService.returnPersonById(id)).willReturn(Optional.empty());
		
        mockMVC.perform(delete(request+"id/"+id))
        	.andExpect(status().isNotFound());
	}
	
	@Test
	void shouldAddEventToPerson() throws Exception{
		given(personService.returnPersonById(1L)).
		willReturn(Optional.of(new Person(1L,"Andrii", "682303412@mail.ru", "682303412", "1111")));
		given(eventService.returnEventById(2L)).
		willReturn(Optional.of(new Event(2L,"Pushkina/40",new Date())));
        mockMVC.perform(post(request+"id/"+1L+"/signPersonToEvent/event/"+2L))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.persons.size()",is(1)));

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
