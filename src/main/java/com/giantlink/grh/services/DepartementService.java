package com.giantlink.grh.services;

import java.util.List;
import java.util.Set;

import com.giantlink.grh.entities.Departement;
import com.giantlink.grh.exceptions.AlreadyExists;
import com.giantlink.grh.exceptions.NotFoundException;
import com.giantlink.grh.models.Requests.DepartementRequest;
import com.giantlink.grh.models.Responses.DepartementResponse;

public interface DepartementService {
	DepartementResponse add(DepartementRequest departementRequest) throws AlreadyExists;

	DepartementResponse get(Integer id) throws NotFoundException;

	DepartementResponse get(String name) throws NotFoundException;

	List<DepartementResponse> get() throws NotFoundException;

	DepartementResponse update(Integer id ,DepartementRequest departementRequest) throws NotFoundException, AlreadyExists;
	void delete(Integer id) throws NotFoundException;
}
