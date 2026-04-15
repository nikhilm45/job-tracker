package com.nikhil.jobtracker.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String companyName;
    @Column(nullable = false)
    private String role;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JobStatus status;
    private LocalDate appliedDate;
    private String jobLink;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
