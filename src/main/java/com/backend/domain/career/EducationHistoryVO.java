package com.backend.domain.career;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EducationHistoryVO {
  private int educationId;
  private int userId;
  private String schoolName;
  private String major;
  private LocalDate startDate;
  private LocalDate endDate;
}
