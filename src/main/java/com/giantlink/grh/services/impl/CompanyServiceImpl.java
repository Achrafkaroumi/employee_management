package com.giantlink.grh.services.impl;

import java.util.List;
import java.util.Set;

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
	public Company add(Company company) {
		return companyRepository.save(company);
	}

	@Override
	public Company get(Integer id) {
		return companyRepository.findById(id).get();
	}

	@Override
	public List<Company> get() {
		return companyRepository.findAll();
	}

	@Override
	public void delete(Integer id) {
		companyRepository.deleteById(id);
	}

}
