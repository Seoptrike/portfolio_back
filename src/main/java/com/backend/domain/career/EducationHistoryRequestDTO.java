package com.backend.domain.career;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EducationHistoryRequestDTO {
    private int educationId;
    private int userId;
    private String username;
    private String schoolName;
    private String major;
    private LocalDate startDate;
    private LocalDate endDate;
}
