package com.giantlink.grh.controllers;

import com.giantlink.grh.entities.Employee;
import com.giantlink.grh.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
	private final EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping
	public ResponseEntity<List<Employee>> get() {
		return new ResponseEntity<List<Employee>>(employeeService.get(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> get(@PathVariable Integer id) {
		return new ResponseEntity<>(employeeService.get(id), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Employee> add(@RequestBody Employee employee) {
		return new ResponseEntity<Employee>(employeeService.add(employee), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Employee> update(@PathVariable Integer id , @RequestBody Employee employee) {
		employee.setId(id);
		return new ResponseEntity<>(employeeService.add(employee), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable Integer id) {
		employeeService.delete(id);
		return new ResponseEntity<>("Employee deleted", HttpStatus.OK);
	}
}
