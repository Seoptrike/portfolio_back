package com.backend.service.career;

import com.backend.domain.career.EducationHistoryRequestDTO;
import com.backend.domain.career.WorkExpRequestDTO;

public interface CareerService {

  void insertWorkExp(WorkExpRequestDTO dto);

  void updateWorkExp(WorkExpRequestDTO dto);

  void deleteWorkExp(int workId);

  void insertEduHistory(EducationHistoryRequestDTO educationHistoryRequestDTO);

  void updateEduHistory(EducationHistoryRequestDTO educationHistoryRequestDTO);

  void deleteEduHistory(int eduId);
}
