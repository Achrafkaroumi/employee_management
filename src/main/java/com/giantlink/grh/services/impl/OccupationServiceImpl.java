package com.giantlink.grh.services.impl;

import com.giantlink.grh.entities.Occupation;
import com.giantlink.grh.exceptions.NotFoundException;
import com.giantlink.grh.mappers.OccupationMapper;
import com.giantlink.grh.models.Requests.OccupationRequest;
import com.giantlink.grh.models.Responses.OccupationResponse;
import com.giantlink.grh.repositories.OccupationRepository;
import com.giantlink.grh.services.OccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OccupationServiceImpl implements OccupationService {
    private final OccupationRepository occupationRepository;

    @Autowired
    public OccupationServiceImpl(OccupationRepository occupationRepository) {
        this.occupationRepository = occupationRepository;
    }

    @Override
    public List<OccupationResponse> get() throws NotFoundException {
        List<Occupation> occupations = occupationRepository.findAll();
        if(occupations.isEmpty()) {
            throw new NotFoundException("No occupations found");
        }
        return OccupationMapper.INSTANCE.occupationListToResponses(occupations);
    }

    @Override
    public OccupationResponse get(Integer id) throws NotFoundException{
        Optional<Occupation> occupation = occupationRepository.findById(id);
        if(!occupation.isPresent()) {
            throw new NotFoundException("Occupation with id : "+id+ " not found");
        }
        return OccupationMapper.INSTANCE.occupationToResponse(occupation.get());
    }

    @Override
    public OccupationResponse add(OccupationRequest occupationRequest) {
        Occupation occupation = OccupationMapper.INSTANCE.requestToOccupation(occupationRequest);
        return OccupationMapper.INSTANCE.occupationToResponse(occupationRepository.save(occupation));
    }

    @Override
    public OccupationResponse update(Integer id, OccupationRequest occupationRequest) throws NotFoundException {
        Occupation occupation = OccupationMapper.INSTANCE.requestToOccupation(occupationRequest);
        if(!occupationRepository.findById(id).isPresent()) {
            throw new NotFoundException("Occupation with id : "+id+ " not found");
        }
        if(occupationRepository.findById(id).isPresent()) {
            occupation.setId(id);
        }
        return OccupationMapper.INSTANCE.occupationToResponse(occupationRepository.save(occupation));
    }

    @Override
    public void delete(Integer id) throws NotFoundException{
        if(!occupationRepository.findById(id).isPresent()){
            throw new NotFoundException("Occupation with id : "+id+ " not found");
        }
        occupationRepository.deleteById(id);
    }
}
