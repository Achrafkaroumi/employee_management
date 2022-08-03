package com.giantlink.grh.controllers;

import com.giantlink.grh.models.Requests.CompanyEntityRequest;
import com.giantlink.grh.models.Responses.CompanyEntityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.giantlink.grh.entities.CompanyEntity;
import com.giantlink.grh.services.CompanyEntityService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/company/entity")
public class CompanyEntityController {

	@Autowired
	CompanyEntityService companyEntityService;

	@GetMapping
	public ResponseEntity<List<CompanyEntityResponse>> getAll(){
		return new ResponseEntity<>(companyEntityService.get(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CompanyEntityResponse> getById(@PathVariable Integer id){
		return new ResponseEntity<>(companyEntityService.get(id), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<CompanyEntityResponse> add(@RequestBody CompanyEntityRequest companyEntityRequest){
		return new ResponseEntity<CompanyEntityResponse>(companyEntityService.add(companyEntityRequest), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<CompanyEntityResponse> update(@PathVariable Integer id, @RequestBody CompanyEntityRequest companyEntity){
		return new ResponseEntity<>(companyEntityService.update(id,companyEntity), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable Integer id){
		companyEntityService.delete(id);
		return new ResponseEntity<>("CompanyEntity deleted", HttpStatus.OK);
	}
}
