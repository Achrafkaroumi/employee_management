package com.giantlink.grh.services;

import java.util.List;

import com.giantlink.grh.entities.CompanyEntity;
import com.giantlink.grh.exceptions.AlreadyExists;
import com.giantlink.grh.exceptions.NotFoundException;
import com.giantlink.grh.models.Requests.CompanyEntityRequest;
import com.giantlink.grh.models.Responses.CompanyEntityResponse;

public interface CompanyEntityService {
	CompanyEntityResponse add(CompanyEntityRequest companyEntity) throws AlreadyExists;

	CompanyEntityResponse get(Integer id) throws NotFoundException;

	List<CompanyEntityResponse> get() throws NotFoundException;

	CompanyEntityResponse update(Integer id, CompanyEntityRequest companyEntity) throws NotFoundException , AlreadyExists;
	void delete(Integer id) throws NotFoundException;
}
