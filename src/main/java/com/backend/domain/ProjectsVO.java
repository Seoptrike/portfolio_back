package com.backend.domain;

import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class ProjectsVO {
    private int projectId;
    private int userId;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String githubUrl;
    private String deployUrl;
    private String notionUrl;
    private String thumbnailUrl;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
