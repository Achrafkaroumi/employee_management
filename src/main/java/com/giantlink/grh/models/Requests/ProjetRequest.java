package com.giantlink.grh.models.Requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProjetRequest {
    @NotNull
    @NotBlank(message = "Project name is required")
    public String projectName;
    @NotNull
    @NotBlank(message = "Project description is required")
    public String projectDesc;
}
