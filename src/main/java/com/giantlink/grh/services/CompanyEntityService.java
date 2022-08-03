package com.giantlink.grh.services;

import java.util.List;

import com.giantlink.grh.entities.CompanyEntity;
import com.giantlink.grh.models.Requests.CompanyEntityRequest;
import com.giantlink.grh.models.Responses.CompanyEntityResponse;

public interface CompanyEntityService {
	CompanyEntityResponse add(CompanyEntityRequest companyEntity);

	CompanyEntityResponse get(Integer id);

	List<CompanyEntityResponse> get();

	CompanyEntityResponse update(Integer id, CompanyEntityRequest companyEntity);
	void delete(Integer id);
}
