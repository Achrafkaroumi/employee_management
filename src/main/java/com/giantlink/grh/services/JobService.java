package com.giantlink.grh.services;

import com.giantlink.grh.entities.Job;

import java.util.List;

public interface JobService {
    List<Job> get();
    Job get(Integer id);
    Job add(Job job);
    void delete(Integer id);
}
