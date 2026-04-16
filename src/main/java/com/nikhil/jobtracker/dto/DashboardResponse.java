package com.nikhil.jobtracker.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponse {
    private int total;
    private int applied;
    private int interview;
    private int offer;
    private int rejected;

}
