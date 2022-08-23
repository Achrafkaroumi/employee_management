package com.giantlink.grh.controllers;

import com.giantlink.grh.exceptions.AlreadyExists;
import com.giantlink.grh.exceptions.NotFoundException;
import com.giantlink.grh.models.Requests.OccupationRequest;
import com.giantlink.grh.models.Responses.OccupationResponse;
import com.giantlink.grh.services.OccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/occupation")
public class OccupationController {
	private final OccupationService occupationService;

	@Autowired
	public OccupationController(OccupationService occupationService) {
		this.occupationService = occupationService;
	}

	@GetMapping
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER','ROLE_MODERATOR')")
	public ResponseEntity<List<OccupationResponse>> get() throws NotFoundException {
		return new ResponseEntity<List<OccupationResponse>>(occupationService.get(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER','ROLE_MODERATOR')")
	public ResponseEntity<OccupationResponse> get(@PathVariable Integer id) throws NotFoundException {
		return new ResponseEntity<>(occupationService.get(id), HttpStatus.OK);
	}

	@PostMapping("/add")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
	public ResponseEntity<OccupationResponse> add(@RequestBody @Valid OccupationRequest occupationRequest) throws AlreadyExists {
		return new ResponseEntity<OccupationResponse>(occupationService.add(occupationRequest), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
	public ResponseEntity<OccupationResponse> update(@PathVariable Integer id , @RequestBody @Valid OccupationRequest occupationRequest) throws NotFoundException, AlreadyExists {
		return new ResponseEntity<>(occupationService.update(id,occupationRequest), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Object> delete(@PathVariable Integer id) throws NotFoundException {
		occupationService.delete(id);
		return new ResponseEntity<>("Occupation deleted", HttpStatus.OK);
	}
}
