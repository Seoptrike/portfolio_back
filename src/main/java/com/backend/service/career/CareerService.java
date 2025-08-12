package com.backend.service.career;

import com.backend.domain.career.EducationHistoryRequestDTO;
import com.backend.domain.career.EducationHistoryVO;
import com.backend.domain.career.WorkExpRequestDTO;
import com.backend.domain.career.WorkExperiencesVO;

import java.util.List;

public interface CareerService {

    void insertWorkExp(WorkExpRequestDTO dto);

    void updateWorkExp(WorkExpRequestDTO dto);

    void deleteWorkExp(int workId);


    void insertEduHistory(EducationHistoryRequestDTO educationHistoryRequestDTO);

    void updateEduHistory(EducationHistoryRequestDTO educationHistoryRequestDTO);

    void deleteEduHistory(int eduId);
}
