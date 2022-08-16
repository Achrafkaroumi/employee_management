package com.giantlink.grh.services;

import java.util.List;

import com.giantlink.grh.entities.Company;
import com.giantlink.grh.exceptions.AlreadyExists;
import com.giantlink.grh.exceptions.NotFoundException;
import com.giantlink.grh.models.Requests.CompanyRequest;
import com.giantlink.grh.models.Responses.CompanyResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyService {

	CompanyResponse add(CompanyRequest company) throws AlreadyExists;

	CompanyResponse get(Integer id) throws NotFoundException;

	List<CompanyResponse> get() throws NotFoundException;

	void delete(Integer id) throws NotFoundException;

	CompanyResponse update(Integer id, CompanyRequest company) throws NotFoundException;

	List<Company> get(Pageable pageable);
}
