package com.giantlink.grh.models.Responses;

import com.giantlink.grh.entities.Occupation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private Integer id;
    private Set<OccupationResponse> occupations;
}
