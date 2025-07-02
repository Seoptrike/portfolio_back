package com.backend.service.career;

import com.backend.domain.EducationHistoryVO;
import com.backend.domain.WorkExperiencesVO;
import com.backend.mapper.EducationHistoryMapper;
import com.backend.mapper.UserMapper;
import com.backend.mapper.WorkExperienceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CareerServiceImpl implements CareerService {
    @Autowired
    WorkExperienceMapper workExperienceMapper;

    @Autowired
    EducationHistoryMapper educationHistoryMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public List<WorkExperiencesVO> findWorkExpByUserId(int userId) {
        return workExperienceMapper.findWorkExpByUserId(userId);
    }

    @Override
    public void insertWorkExp(WorkExperiencesVO wvo) {
        workExperienceMapper.insertWorkExp(wvo);
    }

    @Override
    public void updateWorkExp(WorkExperiencesVO wvo) {
        workExperienceMapper.updateWorkExp(wvo);
    }

    @Override
    public void deleteWorkExp(int workId) {
        workExperienceMapper.deleteWorkExp(workId);
    }

    @Override
    public List<EducationHistoryVO> findEduHistoriesByUserId(int userId) {
        return List.of();
    }

    @Override
    public void insertEduHistory(EducationHistoryVO evo) {

    }

    @Override
    public void updateEduHistory(EducationHistoryVO evo) {

    }

    @Override
    public void deleteEduHistory(int eduId) {

    }
}
