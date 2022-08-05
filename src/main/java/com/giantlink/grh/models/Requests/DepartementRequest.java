package com.giantlink.grh.models.Requests;

import com.giantlink.grh.entities.CompanyEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartementRequest {
    @NotNull
    @NotBlank(message = "Name is required")
    private String name;
    @NotNull(message = "Date is required")
    private Date timestamp;

    private CompanyEntity companyEntity;

}
