package com.backend.service.users;

import com.backend.domain.UsersVO;
import com.backend.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;


@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Autowired
    WorkExperienceMapper workExperienceMapper;
    @Autowired
    EducationHistoryMapper educationHistoryMapper;
    @Autowired
    ProjectMapper projectMapper;
    @Autowired
    UserStackMapper userStackMapper;

    @Override
    public List<UsersVO> getUserAllList() {
        return userMapper.getUserAllList();
    }

    public int findUserID(String username){
        return userMapper.findUserID(username);
    }

    @Override
    public HashMap<String, Object> getUserTotalData(String username) {
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
        result.put("stacks", userStackMapper.selectUserStackByUserId(userID));
        return result;
    }
}
