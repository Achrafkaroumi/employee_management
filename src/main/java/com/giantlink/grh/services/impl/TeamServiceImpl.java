package com.giantlink.grh.services.impl;

import com.giantlink.grh.entities.Team;
import com.giantlink.grh.exceptions.AlreadyExists;
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
    private final TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepo) {
        this.teamRepository = teamRepo;
    }

    @Override
    public List<TeamResponse> get() throws NotFoundException {
        List<Team> listTeam = teamRepository.findAll();
        if (listTeam.isEmpty()) {
            throw new NotFoundException("No teams found");
        }
        return TeamMapper.INSTANCE.teamListToResponse(listTeam);
    }

    @Override
    public TeamResponse get(Integer id) throws NotFoundException {
        Optional<Team> findTeamId = teamRepository.findById(id);
        if (!findTeamId.isPresent()) {
            throw new NotFoundException("Team with id : "+id+ " not found");
        }
        return TeamMapper.INSTANCE.teamToResponse(findTeamId.get());
    }

    @Override
    public TeamResponse add(TeamRequest teamRequest) throws AlreadyExists {
        if(teamRepository.findByTeamName(teamRequest.getTeamName())!=null){
            throw new AlreadyExists("Team with name : "+teamRequest.getTeamName()+" already exists");
        }
        Team team = TeamMapper.INSTANCE.requestToTeam(teamRequest);
        return TeamMapper.INSTANCE.teamToResponse(teamRepository.save(team));
    }

    @Override
    public TeamResponse update(Integer id, TeamRequest teamRequest) throws NotFoundException {
        Team team = TeamMapper.INSTANCE.requestToTeam(teamRequest);
        Optional<Team> findTeamId = teamRepository.findById(id);
        if(!findTeamId.isPresent()){
            throw new NotFoundException("Team with id : "+id+ " not found");
        }
        if(findTeamId.isPresent()) {
                team.setId(id);
        }
        return TeamMapper.INSTANCE.teamToResponse(teamRepository.save(team));
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        if(!teamRepository.findById(id).isPresent()){
            throw new NotFoundException("Team with id : "+id+ " not found");
        }
        teamRepository.deleteById(id);
    }
}
