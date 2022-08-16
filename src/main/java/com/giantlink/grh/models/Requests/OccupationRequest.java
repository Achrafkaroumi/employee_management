package com.giantlink.grh.models.Requests;

import com.giantlink.grh.entities.Employee;
import com.giantlink.grh.entities.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OccupationRequest {
    @NotNull
    private Date dateOccupation;
    @NotNull
    public boolean isCurrent;
    @NotNull
    private String occupationName;
    private Employee employee;
    private Job job;
}
