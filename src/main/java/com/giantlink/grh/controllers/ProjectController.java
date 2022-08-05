package com.giantlink.grh.controllers;

import com.giantlink.grh.entities.Project;
import com.giantlink.grh.entities.Team;
import com.giantlink.grh.exceptions.NotFoundException;
import com.giantlink.grh.models.Requests.ProjetRequest;
import com.giantlink.grh.models.Responses.ProjectResponse;
import com.giantlink.grh.services.ProjectService;
import com.giantlink.grh.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
	private final ProjectService projectService;

	@Autowired
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}

	@GetMapping
	public ResponseEntity<List<ProjectResponse>> get() throws NotFoundException {
		return new ResponseEntity<List<ProjectResponse>>(projectService.get(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProjectResponse> get(@PathVariable Integer id) throws NotFoundException {
		return new ResponseEntity<>(projectService.get(id), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<ProjectResponse> add(@RequestBody ProjetRequest projectRequest) {
		return new ResponseEntity<ProjectResponse>(projectService.add(projectRequest), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<ProjectResponse> update(@PathVariable Integer id , @RequestBody ProjetRequest projectRequest)throws NotFoundException{
		return new ResponseEntity<>(projectService.update(id,projectRequest), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable Integer id) throws NotFoundException{
		projectService.delete(id);
		return new ResponseEntity<>("Project deleted", HttpStatus.OK);
	}
}
