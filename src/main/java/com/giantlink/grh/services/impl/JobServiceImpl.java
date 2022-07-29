package com.giantlink.grh.services.impl;

import com.giantlink.grh.entities.Job;
import com.giantlink.grh.repositories.JobRepository;
import com.giantlink.grh.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> get() {
        return jobRepository.findAll();
    }

    @Override
    public Job get(Integer id) {
        return jobRepository.findById(id).get();
    }

    @Override
    public Job add(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public void delete(Integer id) {
        jobRepository.deleteById(id);
    }
}
