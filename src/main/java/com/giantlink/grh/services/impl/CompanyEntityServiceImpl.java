package com.giantlink.grh.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.giantlink.grh.controllers.advices.CompanyEntityMapper;
import com.giantlink.grh.models.Requests.CompanyEntityRequest;
import com.giantlink.grh.models.Responses.CompanyEntityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.giantlink.grh.entities.CompanyEntity;
import com.giantlink.grh.repositories.CompanyEntityRepository;
import com.giantlink.grh.services.CompanyEntityService;

@Service
public class CompanyEntityServiceImpl implements CompanyEntityService {
	@Autowired
	CompanyEntityRepository companyEntityRepository;

	@Override
	public CompanyEntityResponse add(CompanyEntityRequest companyEntityRequest) {
		CompanyEntity companyEntity = CompanyEntityMapper.INSTANCE.RequestToCompanyEntity(companyEntityRequest);
		return CompanyEntityMapper.INSTANCE.companyEntityToResponse(companyEntityRepository.save(companyEntity));
	}

	@Override
	public CompanyEntityResponse get(Integer id) {
		Optional<CompanyEntity> companyEntity = companyEntityRepository.findById(id);
		return CompanyEntityMapper.INSTANCE.companyEntityToResponse(companyEntity.get());
	}

	@Override
	public List<CompanyEntityResponse> get() {
		List<CompanyEntity> companyEntities = companyEntityRepository.findAll();
		return CompanyEntityMapper.INSTANCE.companyEntityListToResponses(companyEntities);
	}

	@Override
	public CompanyEntityResponse update(Integer id, CompanyEntityRequest companyEntity) {
		CompanyEntity companyEntityToUpdate = CompanyEntityMapper.INSTANCE.RequestToCompanyEntity(companyEntity);
		if(companyEntityRepository.findById(id).isPresent()){
			companyEntityToUpdate.setId(id);
		}
		return CompanyEntityMapper.INSTANCE.companyEntityToResponse(companyEntityRepository.save(companyEntityToUpdate));
	}

	@Override
	public void delete(Integer id) {
		companyEntityRepository.deleteById(id);
	}

}
