package com.backend.domain.career;

import java.time.LocalDate;
import lombok.Data;

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
