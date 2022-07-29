package com.giantlink.grh.services;

import com.giantlink.grh.entities.Project;

import java.util.List;

public interface ProjectService {
    List<Project> get();
    Project get(Integer id);
    Project add(Project project);
    void delete(Integer id);
}
