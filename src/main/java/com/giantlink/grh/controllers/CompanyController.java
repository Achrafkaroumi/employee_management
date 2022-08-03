package com.giantlink.grh.controllers;

import java.util.List;

import com.giantlink.grh.entities.CompanyEntity;
import com.giantlink.grh.models.Requests.CompanyRequest;
import com.giantlink.grh.models.Responses.CompanyResponse;
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
	public ResponseEntity<List<CompanyResponse>> get() {
		return new ResponseEntity<List<CompanyResponse>>(companyService.get(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CompanyResponse> get(@PathVariable Integer id) {
		return new ResponseEntity<>(companyService.get(id), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<CompanyResponse> add(@RequestBody CompanyRequest company) {
		return new ResponseEntity<CompanyResponse>(companyService.add(company), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<CompanyResponse> update(@PathVariable Integer id , @RequestBody CompanyRequest company) {
		return new ResponseEntity<>(companyService.update(id,company), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable Integer id) {
		companyService.delete(id);
		return new ResponseEntity<>("Company deleted", HttpStatus.OK);
	}
}
