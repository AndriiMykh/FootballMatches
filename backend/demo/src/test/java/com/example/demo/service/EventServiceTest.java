package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.DemoApplication;
import com.example.demo.entity.Country;
import com.example.demo.entity.Event;
import com.example.demo.entity.Person;
import com.example.demo.entity.Team;
import com.example.demo.repository.EventRepository;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {
	@Mock
	EventRepository repo;

	@InjectMocks
	EventService service;

	@Test
	void shouldReturnListOfEvents() {
		given(service.returnAllEvents()).willReturn(listEvents());
		List<Event> allEvents = service.returnAllEvents();
		
		assertThat(allEvents).hasSize(3);
	}
	
	@Test
	void shouldReturnEventById() {
		Long eventId = 1L;
		Event event = listEvents().stream().filter(streamEvent-> 
		streamEvent.getId()==eventId).collect(DemoApplication.toSingleton());
		given(service.returnEventById(eventId)).willReturn(Optional.of(event));
		Optional<Event> searchEvent = service.returnEventById(eventId);
		assertThat(searchEvent.get().getPlace()).isEqualTo("Yunosti20");
		
	}
	
	@Test
	void shouldSaveEventSuccessfully() {
		Event firstEvent = new Event(1L,"Yunosti20",15,new Date(),
				new Team(50L, "Legia", Country.POLAND, "someurl"),new Team(51L, "Lech", Country.POLAND, "someurl" ));
		when(service.save(Mockito.any(Event.class))).thenAnswer(i->i.getArgument(0));
		assertThat(service.save(firstEvent)).isInstanceOf(Event.class);
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
