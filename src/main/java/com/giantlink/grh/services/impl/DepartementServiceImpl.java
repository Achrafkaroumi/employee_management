package com.giantlink.grh.services.impl;

import java.util.List;

import com.giantlink.grh.entities.Departement;
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
	public Departement add(Departement departement) {
		return departementRepository.save(departement);
	}

	@Override
	public Departement get(Integer id) {
		return departementRepository.findById(id).get();
	}

	@Override
	public Departement get(String name) {
		return departementRepository.findByName(name).get();
	}

	@Override
	public List<Departement> get() {
		return departementRepository.findAll();
	}

	@Override
	public void delete(Integer id) {
		departementRepository.deleteById(id);
	}

}
