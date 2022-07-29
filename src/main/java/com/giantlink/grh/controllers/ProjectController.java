package com.giantlink.grh.controllers;

import com.giantlink.grh.entities.Project;
import com.giantlink.grh.entities.Team;
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
	public ResponseEntity<List<Project>> get() {
		return new ResponseEntity<List<Project>>(projectService.get(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Project> get(@PathVariable Integer id) {
		return new ResponseEntity<>(projectService.get(id), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Project> add(@RequestBody Project project) {
		return new ResponseEntity<Project>(projectService.add(project), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Project> update(@PathVariable Integer id , @RequestBody Project project) {
		project.setId(id);
		return new ResponseEntity<>(projectService.add(project), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable Integer id) {
		projectService.delete(id);
		return new ResponseEntity<>("Project deleted", HttpStatus.OK);
	}
}
