package com.backend.service.portfolio;

import com.backend.mapper.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PortfolioServiceImpl implements PortfolioService{
    private final UserMapper userMapper;
    private final WorkExperienceMapper workExperienceMapper;
    private final EducationHistoryMapper educationHistoryMapper;
    private final ProjectMapper projectMapper;
    private final StackMapper stackMapper;

    public PortfolioServiceImpl(
            UserMapper userMapper,
            WorkExperienceMapper workExperienceMapper,
            EducationHistoryMapper educationHistoryMapper,
            ProjectMapper projectMapper,
            StackMapper stackMapper
    ) {
        this.userMapper = userMapper;
        this.workExperienceMapper = workExperienceMapper;
        this.educationHistoryMapper = educationHistoryMapper;
        this.projectMapper = projectMapper;
        this.stackMapper = stackMapper;
    }


    public HashMap<String, Object> getUserTotalData(String username){
        int userID = userMapper.findUserID(username);

        HashMap<String, Object> result = new HashMap<>();
        result.put("userID", userID);
        result.put("workExperience", workExperienceMapper.findWorkExpByUserId(userID));
        result.put("educationHistory", educationHistoryMapper.findEduHistoryByUserID(userID));
        result.put("projects", projectMapper.findProjectByUserID(userID));
        result.put("stacks", stackMapper.findStackByUserID(userID));

        return result;
    }
}
