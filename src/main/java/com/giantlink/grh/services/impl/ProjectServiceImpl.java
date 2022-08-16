package com.giantlink.grh.services.impl;

import com.giantlink.grh.entities.Project;
import com.giantlink.grh.exceptions.AlreadyExists;
import com.giantlink.grh.exceptions.NotFoundException;
import com.giantlink.grh.mappers.ProjectMapper;
import com.giantlink.grh.models.Requests.ProjetRequest;
import com.giantlink.grh.models.Responses.ProjectResponse;
import com.giantlink.grh.repositories.ProjectRepository;
import com.giantlink.grh.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<ProjectResponse> get() throws NotFoundException {
        List<Project> findProject = projectRepository.findAll();
        if(findProject.isEmpty()){
            throw new NotFoundException("No Project found");
        }
        return ProjectMapper.INSTANCE.projectListToResponse(findProject);
    }

    @Override
    public ProjectResponse get(Integer id) throws NotFoundException {
        Optional<Project> findProjectId = projectRepository.findById(id);
        if(!findProjectId.isPresent()){
            throw new NotFoundException("Project with id : "+ id+ " not found");
        }
        return ProjectMapper.INSTANCE.projectToResponse(findProjectId.get());
    }

    @Override
    public ProjectResponse add (ProjetRequest projectRequest) throws AlreadyExists {
        if(projectRepository.findByProjectName(projectRequest.projectName)!=null){
            throw new AlreadyExists("Project with name : "+projectRequest.projectName+" already exists");
        }
        Project project = ProjectMapper.INSTANCE.requestToProject(projectRequest);
        return ProjectMapper.INSTANCE.projectToResponse(projectRepository.save(project));
    }

    @Override
    public ProjectResponse update(Integer id, ProjetRequest projetRequest) throws NotFoundException{
        Project project = ProjectMapper.INSTANCE.requestToProject(projetRequest);
        Optional<Project> findById = projectRepository.findById(id);
        if(!findById.isPresent()){
            throw new NotFoundException("Project not found");
        }
        if(findById.isPresent()){
                project.setId(id);
        }
        return ProjectMapper.INSTANCE.projectToResponse(project);
    }

    @Override
    public void delete(Integer id) throws NotFoundException{
        if(!projectRepository.findById(id).isPresent()){
            throw new NotFoundException("Project not found");
        }
        projectRepository.deleteById(id);
    }
}
