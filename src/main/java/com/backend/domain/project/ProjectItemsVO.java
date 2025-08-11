package com.backend.domain.project;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProjectItemsVO {
  private int project_item_id;
  private int user_id;
  private String title;
  private String content;
  private String notion_url;
  private String github_url;
  private LocalDateTime created_at;
  private LocalDateTime updated_at;
}
