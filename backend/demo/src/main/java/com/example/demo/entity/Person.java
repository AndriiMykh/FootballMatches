package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person {
	@Id
	private Long id;
	private String name;
	private String email;
	private String phoneNumber;
	private String password;
}
