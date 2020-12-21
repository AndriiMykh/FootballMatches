package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.Country;
import com.example.demo.entity.Event;
import com.example.demo.entity.Team;
import com.example.demo.service.EventService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventController.class)
class EventControllerTest {

	@Autowired
	private MockMvc mockMVC;
	
	@MockBean
	private EventService eventService;
	
	private String path = "http://localhost:8080/api/events/";
	
    @Autowired
    private ObjectMapper objectMapper;
    
	Event firstEvent;
	Event eventWithTheSameTeams;
	
	@BeforeEach
	void setUp() throws Exception {
	firstEvent = new Event(10L,"Yunosti500",5,new Date(),
				new Team(58L, "Man. City", Country.ENGLAND, "someurl"),new Team(57L, "Man. Utd", Country.ENGLAND, "someurl" ));
	eventWithTheSameTeams = new Event(10L,"Yunosti500",5,new Date(),
			new Team(57L, "Man. Utd", Country.ENGLAND, "someurl" ),new Team(57L, "Man. Utd", Country.ENGLAND, "someurl" ));
	}

	@Test
	void shouldReturnListOfEvents() throws Exception {
		given(eventService.returnAllEvents()).willReturn(listEvents());
		this.mockMVC.perform(get(path))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.size()",is(listEvents().size())));
	}
	@Test
	void shouldSaveEvent() throws Exception {
		this.mockMVC.perform(post(path)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(firstEvent)))
				.andExpect(status().isOk());
	}
	
	@Test
	void shouldThrowTheTeamCantPlayWithItselfException() throws JsonProcessingException, Exception {
		this.mockMVC.perform(post(path)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(eventWithTheSameTeams)))
				.andExpect(status().isConflict());
	}
	private List<Event> listEvents(){
		Event firstEvent = new Event(1L,"Yunosti20",15,new Date(),
				new Team(50L, "Legia", Country.POLAND, "someurl"),new Team(51L, "Lech", Country.POLAND, "someurl" ));
				
		Event secondEvent = new Event(2L,"Yunosti16",15,new Date(),
				new Team(52L, "Shakhtar", Country.UKRAINE, "someurl"),new Team(53L, "Dynamo", Country.UKRAINE, "someurl" ));
		Event thirdEvent = new Event(3L,"Myru3",3,new Date(),
				new Team(54L, "Real Madrid", Country.SPAIN, "someurl"),new Team(55L, "Barcelona", Country.SPAIN, "someurl" ));
		List<Event> events = new ArrayList<>();
		events.add(firstEvent);
		events.add(secondEvent);
		events.add(thirdEvent);
		return events;
	}
}
