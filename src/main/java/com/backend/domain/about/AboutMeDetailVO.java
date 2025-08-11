package com.backend.domain.about;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AboutMeDetailVO {
  private int detailId;
  private int aboutId;
  private String title;
  private String content;
  private int sort;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
