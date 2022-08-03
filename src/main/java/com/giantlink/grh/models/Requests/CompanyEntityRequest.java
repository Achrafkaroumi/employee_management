package com.giantlink.grh.models.Requests;

import com.giantlink.grh.entities.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyEntityRequest {
    @NotNull
    private String name;
    @NotNull
    private Company company;
}
