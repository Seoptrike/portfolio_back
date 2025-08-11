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
  private LocalDate start_date;
  private LocalDate end_date;
  private String github_url;
  private String deploy_url;
  private String notion_url;
  private String thumbnail_url;
  private List<TechStacksVO> stack_names;
}
