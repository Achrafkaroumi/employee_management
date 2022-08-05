package com.giantlink.grh.services.impl;

import java.util.List;
import java.util.Optional;

import com.giantlink.grh.entities.Company;
import com.giantlink.grh.exceptions.AlreadyExists;
import com.giantlink.grh.exceptions.NotFoundException;
import com.giantlink.grh.mappers.CompanyEntityMapper;
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
	public CompanyEntityResponse add(CompanyEntityRequest companyEntityRequest) throws AlreadyExists {
		CompanyEntity companyEntity = CompanyEntityMapper.INSTANCE.RequestToCompanyEntity(companyEntityRequest);
		if(companyEntityRepository.findByName(companyEntity.getName()) != null) {
			throw new AlreadyExists("Company with name " + companyEntity.getName() + " already exists");
		}
		return CompanyEntityMapper.INSTANCE.companyEntityToResponse(companyEntityRepository.save(companyEntity));
	}

	@Override
	public CompanyEntityResponse get(Integer id) throws NotFoundException {
		Optional<CompanyEntity> companyEntity = companyEntityRepository.findById(id);
		if(!companyEntity.isPresent()) {
			throw new NotFoundException("CompanyEntity with id " + id + " not found");
		}
		return CompanyEntityMapper.INSTANCE.companyEntityToResponse(companyEntity.get());
	}

	@Override
	public List<CompanyEntityResponse> get() throws NotFoundException {
		List<CompanyEntity> companyEntities = companyEntityRepository.findAll();
		if(companyEntities.isEmpty()) {
			throw new NotFoundException("No company entities found");
		}
		return CompanyEntityMapper.INSTANCE.companyEntityListToResponses(companyEntities);
	}

	@Override
	public CompanyEntityResponse update(Integer id, CompanyEntityRequest companyEntity) throws NotFoundException , AlreadyExists {
		CompanyEntity companyEntityToUpdate = CompanyEntityMapper.INSTANCE.RequestToCompanyEntity(companyEntity);
		if(!companyEntityRepository.findById(id).isPresent()) {
			throw new NotFoundException("Company with id " + id+ " not found");
		}
		if(companyEntityRepository.findByName(companyEntity.getName()) != null) {
			throw new AlreadyExists("Company with name {" +companyEntity.getName()+ "} already exists");
		}

		if(companyEntityRepository.findById(id).isPresent()){
			companyEntityToUpdate.setId(id);
		}
		return CompanyEntityMapper.INSTANCE.companyEntityToResponse(companyEntityRepository.save(companyEntityToUpdate));
	}

	@Override
	public void delete(Integer id) throws NotFoundException {
		Optional<CompanyEntity> findById = companyEntityRepository.findById(id);
		if(findById.isPresent()==false) {
			throw new NotFoundException("Company entities with id : "+id+ " not found");
		}
		companyEntityRepository.deleteById(id);
	}

}
