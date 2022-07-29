package com.giantlink.grh.services;

import com.giantlink.grh.entities.Team;

import java.util.List;

public interface TeamService {
    List<Team> get();
    Team get(Integer id);
    Team add(Team team);
    void delete(Integer id);
}
