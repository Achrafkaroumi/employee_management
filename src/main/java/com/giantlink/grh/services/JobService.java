package com.giantlink.grh.services;

import com.giantlink.grh.entities.Job;
import com.giantlink.grh.exceptions.NotFoundException;
import com.giantlink.grh.models.Requests.JobRequest;
import com.giantlink.grh.models.Responses.JobResponse;

import java.util.List;

public interface JobService {
    List<JobResponse> get() throws NotFoundException;
    JobResponse get(Integer id) throws NotFoundException;
    JobResponse add(JobRequest jobRequest) ;
    JobResponse update(Integer id , JobRequest jobRequest) throws NotFoundException;
    void delete(Integer id) throws NotFoundException;
}
