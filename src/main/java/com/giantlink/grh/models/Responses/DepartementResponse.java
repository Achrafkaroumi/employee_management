package com.giantlink.grh.models.Responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartementResponse {
    private Integer id;
    private String name;
    private Date timestamp;
    private Set<TeamResponse> teams;

}
