package com.backend.domain.about;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AboutMeVO {
  private int aboutId;
  private int userId;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
