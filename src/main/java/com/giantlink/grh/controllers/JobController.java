package com.giantlink.grh.controllers;

import com.giantlink.grh.entities.Job;
import com.giantlink.grh.entities.Team;
import com.giantlink.grh.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/job")
public class JobController {
	private final JobService jobService;

	@Autowired
	public JobController(JobService jobService) {
		this.jobService = jobService;
	}

	@GetMapping
	public ResponseEntity<List<Job>> get() {
		return new ResponseEntity<List<Job>>(jobService.get(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Job> get(@PathVariable Integer id) {
		return new ResponseEntity<>(jobService.get(id), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Job> add(@RequestBody Job job) {
		return new ResponseEntity<Job>(jobService.add(job), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Job> update(@PathVariable Integer id , @RequestBody Job job) {
		job.setId(id);
		return new ResponseEntity<>(jobService.add(job), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable Integer id) {
		jobService.delete(id);
		return new ResponseEntity<>("Job deleted", HttpStatus.OK);
	}
}
