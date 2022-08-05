package com.giantlink.grh.mappers;

import com.giantlink.grh.entities.Departement;
import com.giantlink.grh.models.Requests.DepartementRequest;
import com.giantlink.grh.models.Responses.DepartementResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartementMapper {
    DepartementMapper INSTANCE = Mappers.getMapper(DepartementMapper.class);

    Departement requestToDepartement(DepartementRequest departementRequest);

    DepartementResponse departementToResponse(Departement departement);

    List<DepartementResponse> departementsListToResponses(List<Departement> departements);
}
