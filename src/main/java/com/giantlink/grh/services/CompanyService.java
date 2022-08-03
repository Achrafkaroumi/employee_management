package com.giantlink.grh.services;

import java.util.List;

import com.giantlink.grh.entities.Company;
import com.giantlink.grh.models.Requests.CompanyRequest;
import com.giantlink.grh.models.Responses.CompanyResponse;

public interface CompanyService {

	CompanyResponse add(CompanyRequest company);

	CompanyResponse get(Integer id);

	List<CompanyResponse> get();

	void delete(Integer id);

	CompanyResponse update(Integer id, CompanyRequest company);
}
