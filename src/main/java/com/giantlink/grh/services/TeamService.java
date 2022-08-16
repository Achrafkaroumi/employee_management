package com.giantlink.grh.services;

import com.giantlink.grh.exceptions.AlreadyExists;
import com.giantlink.grh.exceptions.NotFoundException;
import com.giantlink.grh.models.Requests.TeamRequest;
import com.giantlink.grh.models.Responses.TeamResponse;

import java.util.List;

public interface TeamService {
    List<TeamResponse> get() throws NotFoundException;
    TeamResponse get(Integer id) throws NotFoundException;
    TeamResponse add(TeamRequest teamRequest) throws AlreadyExists;
    TeamResponse update(Integer id , TeamRequest teamRequest) throws NotFoundException;
    void delete(Integer id) throws NotFoundException;
}
