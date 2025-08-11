package com.backend.domain.career;

import java.time.LocalDate;
import java.util.List;

import com.backend.domain.work.WorkDetailsVO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WorkExperiencesVO {
  private int workId;
  private int userId;
  private String companyName;
  private String position;
  private LocalDate startDate;
  private LocalDate endDate;
  private List<WorkDetailsVO> details;
}
