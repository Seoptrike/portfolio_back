package com.backend.domain.project;

import java.security.Timestamp;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

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
  private String thumbnailFileId;
  private Timestamp createdAt;
  private Timestamp updatedAt;
}
