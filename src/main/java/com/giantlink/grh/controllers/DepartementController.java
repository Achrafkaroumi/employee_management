package com.giantlink.grh.controllers;

import com.giantlink.grh.entities.Departement;
import com.giantlink.grh.services.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	public ResponseEntity<List<Departement>> get() {
		return new ResponseEntity<List<Departement>>(departementService.get(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Departement> get(@PathVariable Integer id) {
		return new ResponseEntity<>(departementService.get(id), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Departement> add(@RequestBody Departement departement) {
		return new ResponseEntity<Departement>(departementService.add(departement), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Departement> update(@PathVariable Integer id , @RequestBody Departement departement) {
		departement.setId(id);
		return new ResponseEntity<>(departementService.add(departement), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable Integer id) {
		departementService.delete(id);
		return new ResponseEntity<>("Departement deleted", HttpStatus.OK);
	}
}
