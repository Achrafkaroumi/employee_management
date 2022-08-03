package com.giantlink.grh.services.impl;

import java.util.List;
import java.util.Optional;

import com.giantlink.grh.controllers.advices.CompanyMapper;
import com.giantlink.grh.models.Requests.CompanyRequest;
import com.giantlink.grh.models.Responses.CompanyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giantlink.grh.entities.Company;
import com.giantlink.grh.repositories.CompanyRepository;
import com.giantlink.grh.services.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public CompanyResponse add(CompanyRequest companyRequest) {
		Company company = CompanyMapper.INSTANCE.companyRequestToCompany(companyRequest);
		return CompanyMapper.INSTANCE.companyToCompanyResponse(companyRepository.save(company));
	}

	@Override
	public CompanyResponse get(Integer id) {
		Optional<Company> findById = companyRepository.findById(id);
		return CompanyMapper.INSTANCE.companyToCompanyResponse(findById.get());
	}

	@Override
	public List<CompanyResponse> get() {
		List<Company> companies = companyRepository.findAll();
		return CompanyMapper.INSTANCE.companiesTocompanyResponses(companies);
	}

	@Override
	public void delete(Integer id) {
		companyRepository.deleteById(id);
	}

	@Override
	public CompanyResponse update(Integer id, CompanyRequest company) {
		Company companies = CompanyMapper.INSTANCE.companyRequestToCompany(company);
		if(companyRepository.findById(id).isPresent()){
			companies.setId(id);
			company.setAddress(company.getAddress());
			company.setEmail(company.getEmail());
			company.setName(company.getName());
		}
		return CompanyMapper.INSTANCE.companyToCompanyResponse(companyRepository.save(companies));
	}

}
