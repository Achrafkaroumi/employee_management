package com.giantlink.grh.services;

import com.giantlink.grh.entities.Occupation;
import com.giantlink.grh.exceptions.NotFoundException;
import com.giantlink.grh.models.Requests.OccupationRequest;
import com.giantlink.grh.models.Responses.OccupationResponse;

import java.util.List;

public interface OccupationService {
    List<OccupationResponse> get() throws NotFoundException;
    OccupationResponse get(Integer id) throws NotFoundException;
    OccupationResponse add(OccupationRequest occupationRequest);
    OccupationResponse update(Integer id, OccupationRequest occupationRequest) throws NotFoundException;
    void delete(Integer id) throws NotFoundException;
}
