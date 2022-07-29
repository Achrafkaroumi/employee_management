package com.giantlink.grh.services.impl;

import com.giantlink.grh.entities.Project;
import com.giantlink.grh.repositories.ProjectRepository;
import com.giantlink.grh.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> get() {
        return projectRepository.findAll();
    }

    @Override
    public Project get(Integer id) {
        return projectRepository.findById(id).get();
    }

    @Override
    public Project add (Project project) {
        return projectRepository.save(project);
    }

    @Override
    public void delete(Integer id) {
        projectRepository.deleteById(id);
    }
}
