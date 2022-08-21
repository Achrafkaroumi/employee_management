package com.giantlink.grh.mappers;

import com.giantlink.grh.entities.CompanyImage;
import com.giantlink.grh.models.Responses.CompanyImageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompanyImageMapper {
    CompanyImageMapper INSTANCE = Mappers.getMapper(CompanyImageMapper.class);

    CompanyImageResponse entityToResponse(CompanyImage entity);
}
