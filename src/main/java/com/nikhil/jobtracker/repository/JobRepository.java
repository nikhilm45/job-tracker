package com.nikhil.jobtracker.repository;

import com.nikhil.jobtracker.entity.Job;
import com.nikhil.jobtracker.entity.JobStatus;
import com.nikhil.jobtracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByUser(User user);
    List<Job> findByUserAndStatus(User user, JobStatus status);

}
