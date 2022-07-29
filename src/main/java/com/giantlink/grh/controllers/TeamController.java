package com.giantlink.grh.controllers;

import com.giantlink.grh.entities.Team;
import com.giantlink.grh.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/team")
public class TeamController {
	private final TeamService teamService;

	@Autowired
	public TeamController(TeamService teamService) {
		this.teamService = teamService;
	}

	@GetMapping
	public ResponseEntity<List<Team>> get() {
		return new ResponseEntity<List<Team>>(teamService.get(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Team> get(@PathVariable Integer id) {
		return new ResponseEntity<>(teamService.get(id), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Team> add(@RequestBody Team team) {
		return new ResponseEntity<Team>(teamService.add(team), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Team> update(@PathVariable Integer id , @RequestBody Team team) {
		team.setId(id);
		return new ResponseEntity<>(teamService.add(team), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable Integer id) {
		teamService.delete(id);
		return new ResponseEntity<>("Team deleted", HttpStatus.OK);
	}
}
