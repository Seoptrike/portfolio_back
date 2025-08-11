package com.backend.service.career;

import com.backend.domain.career.EducationHistoryVO;
import com.backend.domain.career.WorkExpRequestDTO;
import com.backend.domain.career.WorkExperiencesVO;

import java.util.List;

public interface CareerService {
    List<WorkExperiencesVO> findWorkExpByUserId(int userId);

    void insertWorkExp(WorkExpRequestDTO dto);

    void updateWorkExp(WorkExpRequestDTO dto);

    void deleteWorkExp(int workId);

    List<EducationHistoryVO> findEduHistoriesByUserId(int userId);

    void insertEduHistory(EducationHistoryVO evo);

    void updateEduHistory(EducationHistoryVO evo);

    void deleteEduHistory(int eduId);
}
