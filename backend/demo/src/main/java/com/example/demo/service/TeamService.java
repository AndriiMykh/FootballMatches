package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Team;
import com.example.demo.repository.TeamRepository;

@Service
@Transactional
public class TeamService {

	@Autowired
	private TeamRepository repo;
	
	public List<Team> returnAllTeams(){
		return (List<Team>) repo.findAll();
	}
	public Optional<Team> returnById(long id){
		return repo.findById(id);
	}
}
