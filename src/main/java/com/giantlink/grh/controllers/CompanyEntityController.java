package com.giantlink.grh.controllers;

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
	public ResponseEntity<List<CompanyEntity>> getAll(){
		return new ResponseEntity<>(companyEntityService.get(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CompanyEntity> getById(@PathVariable Integer id){
		return new ResponseEntity<>(companyEntityService.get(id), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<CompanyEntity> add(@RequestBody CompanyEntity companyEntity){
		return new ResponseEntity<CompanyEntity>(companyEntityService.add(companyEntity), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<CompanyEntity> update(@PathVariable Integer id, @RequestBody CompanyEntity companyEntity){
		companyEntity.setId(id);
		return new ResponseEntity<>(companyEntityService.add(companyEntity), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable Integer id){
		companyEntityService.delete(id);
		return new ResponseEntity<>("CompanyEntity deleted", HttpStatus.OK);
	}
}
