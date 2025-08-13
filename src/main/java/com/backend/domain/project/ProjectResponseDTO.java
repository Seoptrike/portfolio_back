package com.backend.domain.project;

import com.backend.domain.stack.TechStacksVO;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectResponseDTO {
  private int projectId;
  private String title;
  private String description;
  private LocalDate startDate;
  private LocalDate endDate;
  private String githubUrl;
  private String deployUrl;
  private String notionUrl;
  private String thumbnailUrl;
  private String thumbnailFileId;
  private List<TechStacksVO> stackNames;
}
