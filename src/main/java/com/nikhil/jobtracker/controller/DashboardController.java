package com.nikhil.jobtracker.controller;

import com.nikhil.jobtracker.dto.DashboardResponse;
import com.nikhil.jobtracker.entity.User;
import com.nikhil.jobtracker.service.JobService;
import com.nikhil.jobtracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DashboardController {

    @Autowired
    private UserService userService;
    @Autowired
    private JobService jobService;

    @GetMapping("/dashboard")
    public DashboardResponse getDashboard() {
        String email = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        User user = userService.getUserByEmail(email);

        return jobService.getDashboard(user);
    }
}
