package com.giantlink.grh.services.impl;

import java.util.List;
import java.util.Optional;

import com.giantlink.grh.entities.Departement;
import com.giantlink.grh.exceptions.AlreadyExists;
import com.giantlink.grh.exceptions.NotFoundException;
import com.giantlink.grh.mappers.DepartementMapper;
import com.giantlink.grh.models.Requests.DepartementRequest;
import com.giantlink.grh.models.Responses.DepartementResponse;
import com.giantlink.grh.repositories.DepartementRepository;
import com.giantlink.grh.services.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartementServiceImpl implements DepartementService {
	private final DepartementRepository departementRepository;
	@Autowired
	public DepartementServiceImpl(DepartementRepository departementRepository) {
		this.departementRepository = departementRepository;
	}

	@Override
	public DepartementResponse add(DepartementRequest departementRequest) throws AlreadyExists {
		Optional<Departement> departementName = departementRepository.findByName(departementRequest.getName());
		if(departementName.isPresent()) {
			throw new AlreadyExists("Departement with name : { "+departementRequest.getName()+" } already exists");
		}
		Departement departement = DepartementMapper.INSTANCE.requestToDepartement(departementRequest);
		return DepartementMapper.INSTANCE.departementToResponse(departementRepository.save(departement));
	}

	@Override
	public DepartementResponse get(Integer id) throws NotFoundException {
		Optional<Departement> departement = departementRepository.findById(id);
		if(!departement.isPresent()){
			throw  new NotFoundException("Departement with id : "+id+" not found");
		}
		return DepartementMapper.INSTANCE.departementToResponse(departement.get());
	}

	@Override
	public DepartementResponse get(String name) throws NotFoundException {
		Optional<Departement> departementName = departementRepository.findByName(name);
		if (!departementName.isPresent()) {
			throw new NotFoundException("Departement with name : { "+name+" } not found");
		}
		return DepartementMapper.INSTANCE.departementToResponse(departementName.get());
	}

	@Override
	public List<DepartementResponse> get() throws NotFoundException {
		List<Departement> departementsList = departementRepository.findAll();
		if(departementsList.isEmpty()) {
			throw new NotFoundException("No Departement found");
		}
		return DepartementMapper.INSTANCE.departementsListToResponses(departementsList);
	}

	@Override
	public DepartementResponse update(Integer id, DepartementRequest departementRequest) throws NotFoundException , AlreadyExists {
		Departement departement = DepartementMapper.INSTANCE.requestToDepartement(departementRequest);
		if(!departementRepository.findById(id).isPresent()) {
			throw new NotFoundException("Departement with id : "+id+" not found");
		}
		if(departementRepository.findByName(departementRequest.getName()).isPresent()==true) {
			throw new AlreadyExists("Departement with name : { "+departementRequest.getName()+" } already exists");
		}
		if(departementRepository.findById(id).isPresent()) {
			departement.setId(id);
		}
		return DepartementMapper.INSTANCE.departementToResponse(departementRepository.save(departement));
	}

	@Override
	public void delete(Integer id) throws NotFoundException {
		Optional<Departement> findDepartementId =departementRepository.findById(id);
		if(!findDepartementId.isPresent()) {
			throw new NotFoundException("Departement with id : "+id+" not found");
		}
		departementRepository.deleteById(id);
	}

}
