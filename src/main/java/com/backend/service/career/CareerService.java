package com.backend.service.career;

import com.backend.domain.EducationHistoryVO;
import com.backend.domain.WorkExperiencesVO;

import java.util.List;

public interface CareerService {
    List<WorkExperiencesVO> findWorkExpByUserId(int userId);
    void insertWorkExp(WorkExperiencesVO wvo);
    void updateWorkExp(WorkExperiencesVO wvo);
    void deleteWorkExp(int workId);

    List<EducationHistoryVO> findEduHistoriesByUserId(int userId);
    void insertEduHistory(EducationHistoryVO evo);
    void updateEduHistory(EducationHistoryVO evo);
    void deleteEduHistory(int eduId);



}
