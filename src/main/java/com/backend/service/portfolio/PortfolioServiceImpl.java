package com.backend.service.portfolio;

import com.backend.mapper.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
        Integer userID = userMapper.findUserID(username);
        HashMap<String, Object> result = new HashMap<>();
        if (userID == null) {
            result.put("userID", "NONE");
            result.put("workExperience", Collections.emptyList());
            result.put("educationHistory", Collections.emptyList());
            result.put("projects", Collections.emptyList());
            result.put("stacks", Collections.emptyList());
            return result;
        }

        result.put("userID", userID);
        result.put("workExperience", workExperienceMapper.findWorkExpByUserId(userID));
        result.put("educationHistory", educationHistoryMapper.findEduHistoryByUserID(userID));
        result.put("projects", projectMapper.findProjectByUserID(userID));
        result.put("stacks", stackMapper.findStackByUserID(userID));
        return result;
    }
}
