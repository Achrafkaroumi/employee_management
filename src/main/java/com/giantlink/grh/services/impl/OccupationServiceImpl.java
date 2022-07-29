package com.giantlink.grh.services.impl;

import com.giantlink.grh.entities.Occupation;
import com.giantlink.grh.repositories.OccupationRepository;
import com.giantlink.grh.services.OccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OccupationServiceImpl implements OccupationService {
    private final OccupationRepository occupationRepository;

    @Autowired
    public OccupationServiceImpl(OccupationRepository occupationRepository) {
        this.occupationRepository = occupationRepository;
    }

    @Override
    public List<Occupation> get() {
        return occupationRepository.findAll();
    }

    @Override
    public Occupation get(Integer id) {
        return occupationRepository.findById(id).get();
    }

    @Override
    public Occupation add(Occupation occupation) {
        return occupationRepository.save(occupation);
    }

    @Override
    public void delete(Integer id) {
        occupationRepository.deleteById(id);
    }
}
