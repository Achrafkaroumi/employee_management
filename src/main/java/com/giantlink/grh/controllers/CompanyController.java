package com.giantlink.grh.controllers;

import java.util.List;

import com.giantlink.grh.entities.CompanyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.giantlink.grh.entities.Company;
import com.giantlink.grh.services.CompanyService;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {

	@Autowired
	CompanyService companyService;

	@GetMapping
	public ResponseEntity<List<Company>> get() {
		return new ResponseEntity<List<Company>>(companyService.get(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Company> get(@PathVariable Integer id) {
		return new ResponseEntity<>(companyService.get(id), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Company> add(@RequestBody Company company) {
		return new ResponseEntity<Company>(companyService.add(company), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Company> update(@PathVariable Integer id , @RequestBody Company company) {
		company.setId(id);
		return new ResponseEntity<>(companyService.add(company), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable Integer id) {
		companyService.delete(id);
		return new ResponseEntity<>("Company deleted", HttpStatus.OK);
	}
}
