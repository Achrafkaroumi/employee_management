package com.giantlink.grh.mappers;

import com.giantlink.grh.entities.CompanyEntity;
import com.giantlink.grh.models.Requests.CompanyEntityRequest;
import com.giantlink.grh.models.Responses.CompanyEntityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyEntityMapper {
    CompanyEntityMapper INSTANCE = Mappers.getMapper(CompanyEntityMapper.class);

    CompanyEntity RequestToCompanyEntity (CompanyEntityRequest companyEntityRequest);
    CompanyEntityResponse companyEntityToResponse (CompanyEntity companyEntity);

    List<CompanyEntityResponse> companyEntityListToResponses(List<CompanyEntity> companyEntities);
}
