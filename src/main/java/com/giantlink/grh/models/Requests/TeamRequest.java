package com.giantlink.grh.models.Requests;

import com.giantlink.grh.entities.Departement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamRequest {
    @NotNull
    @NotBlank(message = "Team name is required")
    private String teamName;

    @NotNull
    @NotBlank(message = "Team description is required")
    private String teamDesc;
    @NotNull
    private Departement departement;
}
