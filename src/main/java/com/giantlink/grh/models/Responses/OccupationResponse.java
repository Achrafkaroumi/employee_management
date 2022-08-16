package com.giantlink.grh.models.Responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OccupationResponse {
    private Integer id;
    private Date dateOccupation;
    private boolean isCurrent;
    private String occupationName;
}
