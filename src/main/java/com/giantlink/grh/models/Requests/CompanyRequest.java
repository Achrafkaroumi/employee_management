package com.giantlink.grh.models.Requests;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.giantlink.grh.entities.CompanyEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequest {
    @NotNull
    private String name;
    @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z]{2,4}$")
    @NotNull
    private String email;
    @NotNull
    private String address;

}
