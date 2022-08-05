package com.giantlink.grh.controllers;

import com.giantlink.grh.entities.Departement;
import com.giantlink.grh.exceptions.AlreadyExists;
import com.giantlink.grh.exceptions.NotFoundException;
import com.giantlink.grh.models.Requests.DepartementRequest;
import com.giantlink.grh.models.Responses.DepartementResponse;
import com.giantlink.grh.services.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/departement")
public class DepartementController {
	private final DepartementService departementService;

	@Autowired
	public DepartementController(DepartementService departementService) {
		this.departementService = departementService;
	}

	@GetMapping
	public ResponseEntity<List<DepartementResponse>> get() throws NotFoundException {
		return new ResponseEntity<List<DepartementResponse>>(departementService.get(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DepartementResponse> get(@PathVariable Integer id) throws NotFoundException {
		return new ResponseEntity<>(departementService.get(id), HttpStatus.OK);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<DepartementResponse> get(@PathVariable @Valid String name) throws NotFoundException {
		return new ResponseEntity<>(departementService.get(name), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<DepartementResponse> add(@RequestBody @Valid DepartementRequest departementRequest) throws AlreadyExists {
		return new ResponseEntity<DepartementResponse>(departementService.add(departementRequest), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<DepartementResponse> update(@PathVariable Integer id , @RequestBody @Valid DepartementRequest departementRequest) throws NotFoundException , AlreadyExists {
		return new ResponseEntity<>(departementService.update(id,departementRequest), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable Integer id) throws NotFoundException {
		departementService.delete(id);
		return new ResponseEntity<>("Departement deleted", HttpStatus.OK);
	}
}
