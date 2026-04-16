package com.nikhil.jobtracker.service;

import com.nikhil.jobtracker.dto.DashboardResponse;
import com.nikhil.jobtracker.dto.JobResponse;
import com.nikhil.jobtracker.entity.Job;
import com.nikhil.jobtracker.entity.JobStatus;
import com.nikhil.jobtracker.entity.User;
import com.nikhil.jobtracker.repository.JobRepository;
import com.nikhil.jobtracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private UserRepository userRepository;

    public Job addJob(Job job, User user) {
        job.setUser(user);
        return jobRepository.save(job);
    }

    public List<Job> getJobsByUser(User user) {
        return jobRepository.findByUser(user);
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));
    }

    public DashboardResponse getDashboard(User user) {

        List<Job> jobs = jobRepository.findByUser(user);
        int total = jobs.size();
        int applied = 0;
        int interview = 0;
        int offer = 0;
        int rejected = 0;

        for (Job job : jobs) {
            switch (job.getStatus()) {
                case APPLIED -> applied++;
                case INTERVIEW -> interview++;
                case OFFER -> offer++;
                case REJECTED -> rejected++;
            }
        }
        return new DashboardResponse(total, applied, interview, offer, rejected);
    }

    public Page<JobResponse> getJobs(User user, JobStatus status, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Job> jobPage;

        if (status != null) {
            jobPage = jobRepository.findByUserAndStatus(user, status, pageable);
        } else {
            jobPage = jobRepository.findByUser(user, pageable);
        }

        return jobPage.map(this::mapToResponse);
    }

    private JobResponse mapToResponse(Job job) {
        return new JobResponse(
                job.getId(),
                job.getCompanyName(),
                job.getRole(),
                job.getStatus(),
                job.getJobLink()
        );
    }
}
