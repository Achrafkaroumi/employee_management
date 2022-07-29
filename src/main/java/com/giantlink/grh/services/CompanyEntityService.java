package com.giantlink.grh.services;

import java.util.List;

import com.giantlink.grh.entities.CompanyEntity;

public interface CompanyEntityService {
	CompanyEntity add(CompanyEntity companyEntity);

	CompanyEntity get(Integer id);

	List<CompanyEntity> get();

	void delete(Integer id);
}
