package com.giantlink.grh.controllers;

import com.giantlink.grh.exceptions.AlreadyExists;
import com.giantlink.grh.exceptions.NotFoundException;
import com.giantlink.grh.models.Requests.EmployeeRequest;
import com.giantlink.grh.models.Responses.EmployeeResponse;
import com.giantlink.grh.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
	private final EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping
	public ResponseEntity<List<EmployeeResponse>> get() throws NotFoundException{
		return new ResponseEntity<List<EmployeeResponse>>(employeeService.get(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponse> get(@PathVariable Integer id) throws NotFoundException {
		return new ResponseEntity<>(employeeService.get(id), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<EmployeeResponse> add(@RequestBody @Valid EmployeeRequest employeeRequest) throws AlreadyExists {
		return new ResponseEntity<EmployeeResponse>(employeeService.add(employeeRequest), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<EmployeeResponse> update(@PathVariable Integer id , @RequestBody EmployeeRequest employeeRequest)throws NotFoundException,AlreadyExists{
		return new ResponseEntity<>(employeeService.update(id,employeeRequest), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable Integer id) throws NotFoundException{
		employeeService.delete(id);
		return new ResponseEntity<>("Employee deleted", HttpStatus.OK);
	}
}
