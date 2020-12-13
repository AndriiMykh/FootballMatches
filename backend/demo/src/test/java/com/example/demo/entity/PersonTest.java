package com.example.demo.entity;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PersonTest {

	Person person;
	@BeforeEach
	void setUp() throws Exception {
		person = new Person("Andrii", "andrii@mail.ru", "729784231", "1111");
	}

	@Test
	void shouldCreatePersonWithAConstructor() {
		assertAll(
					()->assertThat(person.getName(),equalTo("Andrii")),
					()->assertThat(person.getEmail(),equalTo("andrii@mail.ru")),
					()->assertThat(person.getPhoneNumber(),equalTo("729784231")),
					()->assertThat(person.getPassword(),equalTo("1111"))
				);
	}

}
