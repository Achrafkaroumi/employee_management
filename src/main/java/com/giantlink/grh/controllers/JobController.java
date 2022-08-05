package com.giantlink.grh.controllers;

import com.giantlink.grh.entities.Job;
import com.giantlink.grh.entities.Team;
import com.giantlink.grh.exceptions.NotFoundException;
import com.giantlink.grh.models.Requests.JobRequest;
import com.giantlink.grh.models.Responses.JobResponse;
import com.giantlink.grh.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
	public ResponseEntity<List<JobResponse>> get() throws NotFoundException {
		return new ResponseEntity<List<JobResponse>>(jobService.get(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<JobResponse> get(@PathVariable Integer id) throws NotFoundException{
		return new ResponseEntity<>(jobService.get(id), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<JobResponse> add(@RequestBody @Valid JobRequest jobRequest) {
		return new ResponseEntity<JobResponse>(jobService.add(jobRequest), HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<JobResponse> update(@PathVariable Integer id , @RequestBody JobRequest jobRequest) throws NotFoundException{
		return new ResponseEntity<>(jobService.update(id,jobRequest), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable Integer id) throws NotFoundException{
		jobService.delete(id);
		return new ResponseEntity<>("Job deleted", HttpStatus.OK);
	}
}
