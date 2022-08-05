package com.giantlink.grh.services.impl;

import java.util.List;
import java.util.Optional;

import com.giantlink.grh.mappers.CompanyMapper;
import com.giantlink.grh.exceptions.AlreadyExists;
import com.giantlink.grh.exceptions.NotFoundException;
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
	public CompanyResponse add(CompanyRequest companyRequest) throws AlreadyExists {
		Company company = CompanyMapper.INSTANCE.companyRequestToCompany(companyRequest);
		if(companyRepository.findByName(company.getName())!= null) {
			throw new AlreadyExists("Company with name : {"+company.getName()+ "} already exists");
		}
		return CompanyMapper.INSTANCE.companyToCompanyResponse(companyRepository.save(company));
	}

	@Override
	public CompanyResponse get(Integer id) throws NotFoundException {
		Optional<Company> findById = companyRepository.findById(id);
		if(findById.isPresent()==false) {
			throw new NotFoundException("Company with id : "+id+ "not found");
		}
		return CompanyMapper.INSTANCE.companyToCompanyResponse(findById.get());
	}

	@Override
	public List<CompanyResponse> get() throws NotFoundException{
		if(companyRepository.findAll().isEmpty()) {
			throw new NotFoundException("Companies not found");
		}
		List<Company> companies = companyRepository.findAll();
		return CompanyMapper.INSTANCE.companiesToCompanyResponses(companies);
	}

	@Override
	public void delete(Integer id) throws NotFoundException {
		Optional<Company> findById = companyRepository.findById(id);
		if(findById.isPresent()==false) {
			throw new NotFoundException("Company with id : "+id+ " not found");
		}
		companyRepository.deleteById(id);
	}

	@Override
	public CompanyResponse update(Integer id, CompanyRequest company) throws AlreadyExists, NotFoundException {
		Company companies = CompanyMapper.INSTANCE.companyRequestToCompany(company);
		if(companyRepository.findById(id).isPresent()==false) {
			throw new NotFoundException("Company with id : "+id+ "not found");
		}

		if(companyRepository.findByName(companies.getName())!= null) {
			throw new AlreadyExists("Company with name : {"+companies.getName()+ "} already exists");
		}

		if(companyRepository.findById(id).isPresent()){
			companies.setId(id);
		}
		return CompanyMapper.INSTANCE.companyToCompanyResponse(companyRepository.save(companies));
	}

}
