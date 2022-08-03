package com.giantlink.grh.controllers.advices;

import com.giantlink.grh.entities.Company;
import com.giantlink.grh.models.Requests.CompanyRequest;
import com.giantlink.grh.models.Responses.CompanyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    Company companyRequestToCompany (CompanyRequest company);
    CompanyResponse companyResponseToCompany (Company company);

    List<CompanyResponse> companiesTocompanyResponses(List<Company> companies);
}
