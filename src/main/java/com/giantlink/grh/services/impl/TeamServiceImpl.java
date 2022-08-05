package com.giantlink.grh.services.impl;

import com.giantlink.grh.entities.Team;
import com.giantlink.grh.exceptions.NotFoundException;
import com.giantlink.grh.mappers.TeamMapper;
import com.giantlink.grh.models.Requests.TeamRequest;
import com.giantlink.grh.models.Responses.TeamResponse;
import com.giantlink.grh.repositories.TeamRepository;
import com.giantlink.grh.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository TeamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepo) {
        this.TeamRepository = teamRepo;
    }

    @Override
    public List<TeamResponse> get() throws NotFoundException {
        List<Team> listTeam = TeamRepository.findAll();
        if (listTeam.isEmpty()) {
            throw new NotFoundException("No teams found");
        }
        return TeamMapper.INSTANCE.teamListToResponse(listTeam);
    }

    @Override
    public TeamResponse get(Integer id) throws NotFoundException {
        Optional<Team> findTeamId = TeamRepository.findById(id);
        if (!findTeamId.isPresent()) {
            throw new NotFoundException("Team with id : "+id+ " not found");
        }
        return TeamMapper.INSTANCE.teamToResponse(findTeamId.get());
    }

    @Override
    public TeamResponse add(TeamRequest teamRequest) {
        Team team = TeamMapper.INSTANCE.requestToTeam(teamRequest);
        return TeamMapper.INSTANCE.teamToResponse(TeamRepository.save(team));
    }

    @Override
    public TeamResponse update(Integer id, TeamRequest teamRequest) throws NotFoundException {
        Team team = TeamMapper.INSTANCE.requestToTeam(teamRequest);
        if(!TeamRepository.findById(id).isPresent()){
            throw new NotFoundException("Team with id : "+id+ " not found");
        }
        if(TeamRepository.findById(id).isPresent()) {
            team.setId(id);
        }
        return TeamMapper.INSTANCE.teamToResponse(TeamRepository.save(team));
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        if(!TeamRepository.findById(id).isPresent()){
            throw new NotFoundException("Team with id : "+id+ " not found");
        }
        TeamRepository.deleteById(id);
    }
}
