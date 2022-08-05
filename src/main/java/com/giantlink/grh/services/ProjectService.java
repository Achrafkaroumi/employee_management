package com.giantlink.grh.services;

import com.giantlink.grh.exceptions.NotFoundException;
import com.giantlink.grh.models.Requests.ProjetRequest;
import com.giantlink.grh.models.Responses.ProjectResponse;

import java.util.List;

public interface ProjectService {
    List<ProjectResponse> get() throws NotFoundException;
    ProjectResponse get(Integer id) throws NotFoundException;
    ProjectResponse add(ProjetRequest projetRequest);

    ProjectResponse update(Integer id , ProjetRequest projetRequest) throws NotFoundException;
    void delete(Integer id) throws NotFoundException;
}
