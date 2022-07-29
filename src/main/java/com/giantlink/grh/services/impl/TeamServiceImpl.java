package com.giantlink.grh.services.impl;

import com.giantlink.grh.entities.Team;
import com.giantlink.grh.repositories.TeamRepository;
import com.giantlink.grh.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository TeamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepo) {
        this.TeamRepository = teamRepo;
    }

    @Override
    public List<Team> get() {
        return TeamRepository.findAll();
    }

    @Override
    public Team get(Integer id) {
        return TeamRepository.findById(id).get();
    }

    @Override
    public Team add(Team team) {
        return TeamRepository.save(team);
    }

    @Override
    public void delete(Integer id) {
        TeamRepository.deleteById(id);
    }
}
