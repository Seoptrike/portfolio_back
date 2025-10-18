package com.backend.domain.career;

import com.backend.domain.work.WorkDetailsVO;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
public class WorkExpRequestDTO {
  private int workId;
  private String username;
  private String companyName;
  private String position;
  private LocalDate startDate;
  private LocalDate endDate;
  private List<WorkDetailsVO> details;
}
