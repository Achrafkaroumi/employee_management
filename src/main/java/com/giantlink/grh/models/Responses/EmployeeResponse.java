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
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Set<OccupationResponse> occupations;
}
