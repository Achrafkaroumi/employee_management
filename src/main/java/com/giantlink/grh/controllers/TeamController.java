package com.giantlink.grh.controllers;

import com.giantlink.grh.exceptions.AlreadyExists;
import com.giantlink.grh.exceptions.NotFoundException;
import com.giantlink.grh.models.Requests.TeamRequest;
import com.giantlink.grh.models.Responses.TeamResponse;
import com.giantlink.grh.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/team")
public class TeamController {
	private final TeamService teamService;

	@Autowired
	public TeamController(TeamService teamService) {
		this.teamService = teamService;
	}

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER','ROLE_MODERATOR')")
	public ResponseEntity<List<TeamResponse>> get() throws NotFoundException {
		return new ResponseEntity<List<TeamResponse>>(teamService.get(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER','ROLE_MODERATOR')")
	public ResponseEntity<TeamResponse> get(@PathVariable Integer id) throws NotFoundException {
		return new ResponseEntity<>(teamService.get(id), HttpStatus.OK);
	}

	@PostMapping("/add")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
	public ResponseEntity<TeamResponse> add(@RequestBody @Valid TeamRequest teamRequest) throws AlreadyExists {
		return new ResponseEntity<TeamResponse>(teamService.add(teamRequest), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
	public ResponseEntity<TeamResponse> update(@PathVariable Integer id , @RequestBody @Valid TeamRequest teamRequest) throws NotFoundException, AlreadyExists {
		return new ResponseEntity<>(teamService.update(id,teamRequest), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> delete(@PathVariable Integer id) throws NotFoundException {
		teamService.delete(id);
		return new ResponseEntity<>("Team deleted", HttpStatus.OK);
	}
}
