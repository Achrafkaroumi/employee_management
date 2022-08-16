package com.giantlink.grh.models.Responses;

import com.giantlink.grh.entities.Job;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {
    private Integer id;
    private String projectName;
    private String projectDesc;
    private Set<JobResponse> jobs;
}
