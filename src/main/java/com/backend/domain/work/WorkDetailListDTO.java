package com.backend.domain.work;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkDetailListDTO {
  private int workId;
  private String companyName;
  private String position;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private List<WorkDetailsVO> details;
}
