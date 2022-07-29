package com.giantlink.grh.controllers;

import com.giantlink.grh.entities.Occupation;
import com.giantlink.grh.entities.Team;
import com.giantlink.grh.services.OccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/occupation")
public class OccupationController {
	private final OccupationService occupationService;

	@Autowired
	public OccupationController(OccupationService occupationService) {
		this.occupationService = occupationService;
	}

	@GetMapping
	public ResponseEntity<List<Occupation>> get() {
		return new ResponseEntity<List<Occupation>>(occupationService.get(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Occupation> get(@PathVariable Integer id) {
		return new ResponseEntity<>(occupationService.get(id), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Occupation> add(@RequestBody Occupation occupation) {
		return new ResponseEntity<Occupation>(occupationService.add(occupation), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Occupation> update(@PathVariable Integer id , @RequestBody Occupation occupation) {
		occupation.setId(id);
		return new ResponseEntity<>(occupationService.add(occupation), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable Integer id) {
		occupationService.delete(id);
		return new ResponseEntity<>("Occupation deleted", HttpStatus.OK);
	}
}
