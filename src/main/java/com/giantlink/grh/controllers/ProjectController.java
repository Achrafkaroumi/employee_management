package com.giantlink.grh.controllers;

import com.giantlink.grh.exceptions.AlreadyExists;
import com.giantlink.grh.exceptions.NotFoundException;
import com.giantlink.grh.models.Requests.ProjetRequest;
import com.giantlink.grh.models.Responses.ProjectResponse;
import com.giantlink.grh.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
	private final ProjectService projectService;

	@Autowired
	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER','ROLE_MODERATOR')")
	public ResponseEntity<List<ProjectResponse>> get() throws NotFoundException {
		return new ResponseEntity<List<ProjectResponse>>(projectService.get(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER','ROLE_MODERATOR')")
	public ResponseEntity<ProjectResponse> get(@PathVariable Integer id) throws NotFoundException {
		return new ResponseEntity<>(projectService.get(id), HttpStatus.OK);
	}

	@PostMapping("/add")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
	public ResponseEntity<ProjectResponse> add(@RequestBody ProjetRequest projectRequest) throws AlreadyExists {
		return new ResponseEntity<ProjectResponse>(projectService.add(projectRequest), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
	public ResponseEntity<ProjectResponse> update(@PathVariable Integer id , @RequestBody ProjetRequest projectRequest)throws NotFoundException, AlreadyExists{
		return new ResponseEntity<>(projectService.update(id,projectRequest), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> delete(@PathVariable Integer id) throws NotFoundException{
		projectService.delete(id);
		return new ResponseEntity<>("Project deleted", HttpStatus.OK);
	}
}
