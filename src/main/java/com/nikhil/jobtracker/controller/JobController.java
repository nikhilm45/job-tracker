package com.nikhil.jobtracker.controller;

import com.nikhil.jobtracker.entity.Job;
import com.nikhil.jobtracker.entity.User;
import com.nikhil.jobtracker.service.JobService;
import com.nikhil.jobtracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
