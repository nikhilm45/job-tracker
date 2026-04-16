package com.nikhil.jobtracker.dto;

import com.nikhil.jobtracker.entity.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JobResponse {

    private Long id;
    private String companyName;
    private String role;
    private JobStatus status;
    private String jobLink;
}