package com.nikhil.jobtracker.controller;

import com.nikhil.jobtracker.dto.DashboardResponse;
import com.nikhil.jobtracker.dto.JobResponse;
import com.nikhil.jobtracker.entity.Job;
import com.nikhil.jobtracker.entity.JobStatus;
import com.nikhil.jobtracker.entity.User;
import com.nikhil.jobtracker.service.JobService;
import com.nikhil.jobtracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private UserService userService;

    @PostMapping
    public Job addJob(@RequestBody Job job, @RequestParam Long userId) {
        User user = userService.getUserById(userId);
        return jobService.addJob(job, user);
    }

    @GetMapping("/{userId}")
    public List<Job> getJobsByUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        return jobService.getJobsByUser(user);
    }

    @GetMapping
    public Page<JobResponse> getJobs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) JobStatus status
    ) {

        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal == null || principal.equals("anonymousUser")) {
            throw new RuntimeException("Unauthorized");
        }

        String email = principal.toString();

        User user = userService.getUserByEmail(email);

        return jobService.getJobs(user, status, page, size);
    }
}
