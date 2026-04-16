package com.nikhil.jobtracker.service;

import com.nikhil.jobtracker.dto.DashboardResponse;
import com.nikhil.jobtracker.entity.Job;
import com.nikhil.jobtracker.entity.User;
import com.nikhil.jobtracker.repository.JobRepository;
import com.nikhil.jobtracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
