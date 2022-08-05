package com.giantlink.grh.mappers;

import com.giantlink.grh.entities.Project;
import com.giantlink.grh.models.Requests.ProjetRequest;
import com.giantlink.grh.models.Responses.ProjectResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    Project requestToProject(ProjetRequest projetRequest);
    ProjectResponse projectToResponse(Project Project);

    List<ProjectResponse> projectListToResponse(List<Project>Project);
}
