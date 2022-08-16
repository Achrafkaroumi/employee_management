package com.giantlink.grh.controllers;

import com.giantlink.grh.exceptions.AlreadyExists;
import com.giantlink.grh.exceptions.NotFoundException;
import com.giantlink.grh.models.Requests.CompanyEntityRequest;
import com.giantlink.grh.models.Responses.CompanyEntityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.giantlink.grh.entities.CompanyEntity;
import com.giantlink.grh.services.CompanyEntityService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/company/entity")
public class CompanyEntityController {

	@Autowired
	CompanyEntityService companyEntityService;

	@GetMapping
	public ResponseEntity<List<CompanyEntityResponse>> getAll() throws NotFoundException {
		return new ResponseEntity<>(companyEntityService.get(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CompanyEntityResponse> getById(@PathVariable Integer id) throws NotFoundException{
		return new ResponseEntity<>(companyEntityService.get(id), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<CompanyEntityResponse> add(@RequestBody @Valid CompanyEntityRequest companyEntityRequest) throws AlreadyExists {
		return new ResponseEntity<CompanyEntityResponse>(companyEntityService.add(companyEntityRequest), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<CompanyEntityResponse> update(@PathVariable Integer id, @RequestBody @Valid CompanyEntityRequest companyEntity) throws NotFoundException , AlreadyExists {
		return new ResponseEntity<>(companyEntityService.update(id,companyEntity), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable Integer id) throws NotFoundException {
		companyEntityService.delete(id);
		return new ResponseEntity<>("CompanyEntity deleted", HttpStatus.OK);
	}
}
