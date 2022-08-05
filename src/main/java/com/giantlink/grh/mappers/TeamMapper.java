package com.giantlink.grh.mappers;

import com.giantlink.grh.entities.Team;
import com.giantlink.grh.models.Requests.TeamRequest;
import com.giantlink.grh.models.Responses.TeamResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    Team requestToTeam(TeamRequest teamRequest);
    TeamResponse teamToResponse(Team team);

    List<TeamResponse> teamListToResponse(List<Team> teams);
}
