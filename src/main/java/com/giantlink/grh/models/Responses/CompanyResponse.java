package com.giantlink.grh.models.Responses;

import com.giantlink.grh.entities.CompanyEntity;
import com.giantlink.grh.entities.CompanyImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponse {
    private Integer id;
    private String name;
    private String email;
    private String address;
    private String phone;
    private Set<CompanyEntity> entities;
    private Set<CompanyImage> images;
}
