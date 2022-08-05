package com.giantlink.grh.mappers;

import com.giantlink.grh.entities.Occupation;
import com.giantlink.grh.models.Requests.OccupationRequest;
import com.giantlink.grh.models.Responses.OccupationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OccupationMapper {
    OccupationMapper INSTANCE = Mappers.getMapper(OccupationMapper.class);

    Occupation requestToOccupation(OccupationRequest occupationRequest);
    OccupationResponse occupationToResponse(Occupation occupation);

    List<OccupationResponse> occupationListToResponses(List<Occupation> occupations);
}
