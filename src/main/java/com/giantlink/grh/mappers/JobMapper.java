package com.giantlink.grh.mappers;

import com.giantlink.grh.entities.Job;
import com.giantlink.grh.models.Requests.JobRequest;
import com.giantlink.grh.models.Responses.JobResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobMapper {
    JobMapper INSTANCE = org.mapstruct.factory.Mappers.getMapper(JobMapper.class);
    Job requestToJob (JobRequest jobRequest);
    JobResponse jobToResponse (Job job);
    List<JobResponse> jobListToResponse (List<Job> jobList);
}
