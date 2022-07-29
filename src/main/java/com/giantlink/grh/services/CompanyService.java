package com.giantlink.grh.services;

import java.util.List;

import com.giantlink.grh.entities.Company;

public interface CompanyService {

	Company add(Company company);

	Company get(Integer id);

	List<Company> get();

	void delete(Integer id);
}
