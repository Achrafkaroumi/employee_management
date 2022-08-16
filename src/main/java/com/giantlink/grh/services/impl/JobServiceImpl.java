package com.giantlink.grh.services.impl;

import com.giantlink.grh.entities.Job;
import com.giantlink.grh.exceptions.AlreadyExists;
import com.giantlink.grh.exceptions.NotFoundException;
import com.giantlink.grh.mappers.JobMapper;
import com.giantlink.grh.models.Requests.JobRequest;
import com.giantlink.grh.models.Responses.JobResponse;
import com.giantlink.grh.repositories.JobRepository;
import com.giantlink.grh.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobResponse> get() throws NotFoundException {
        if(jobRepository.findAll().isEmpty()) {
            throw new NotFoundException("No jobs found");
        }
        return JobMapper.INSTANCE.jobListToResponse(jobRepository.findAll());
    }

    @Override
    public JobResponse get(Integer id) throws NotFoundException {
        Optional<Job> jobById = jobRepository.findById(id);
        if(!jobById.isPresent()) {
            throw new NotFoundException("Job not found");
        }
        return JobMapper.INSTANCE.jobToResponse(jobById.get());
    }

    @Override
    public JobResponse add(JobRequest jobRequest) throws AlreadyExists {
        if(jobRepository.findByName(jobRequest.getName())!=null) {
            throw new AlreadyExists("Job with name : "+jobRequest.getName()+" already exists");
        }
        Job job = JobMapper.INSTANCE.requestToJob(jobRequest);
        return JobMapper.INSTANCE.jobToResponse(jobRepository.save(job));
    }

    @Override
    public JobResponse update(Integer id, JobRequest jobRequest) throws NotFoundException{
        Job job = JobMapper.INSTANCE.requestToJob(jobRequest);
        Optional<Job> findById = jobRepository.findById(id);
        if(!findById.isPresent()) {
            throw new NotFoundException("Job not found");
        }
        if(findById.isPresent()){
                job.setId(id);
        }
        return JobMapper.INSTANCE.jobToResponse(jobRepository.save(job));
    }

    @Override
    public void delete(Integer id) throws NotFoundException{
        if(!jobRepository.findById(id).isPresent()) {
            throw new NotFoundException("Job not found");
        }
        jobRepository.deleteById(id);
    }
}
