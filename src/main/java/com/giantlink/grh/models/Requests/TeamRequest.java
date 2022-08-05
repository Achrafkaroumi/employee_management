package com.giantlink.grh.models.Requests;

import com.giantlink.grh.entities.Departement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamRequest {
    @NotNull
    private Departement departement;
}
