package com.backend.service.users;

import com.backend.domain.project.ProjectResponseDTO;
import com.backend.domain.user.UsersVO;
import com.backend.mapper.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  @Autowired private UserMapper userMapper;
  @Autowired WorkExperienceMapper workExperienceMapper;
  @Autowired EducationHistoryMapper educationHistoryMapper;
  @Autowired ProjectMapper projectMapper;
  @Autowired UserStackMapper userStackMapper;
  @Autowired AboutMeMapper aboutMeMapper;

  @Override
  public List<UsersVO> getUserAllList() {
    return userMapper.getUserAllList();
  }

  public int findUserID(String username) {
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
      result.put("about", Collections.emptyList());
      return result;
    }
    result.put("userID", userID);
    result.put("workExperience", workExperienceMapper.findWorkExpByUserId(userID));
    result.put("educationHistory", educationHistoryMapper.findEduHistoryByUserID(userID));
    result.put("projects", projectMapper.selectProjectsByUserId(userID));
    result.put("stacks", userStackMapper.selectUserStackByUserId(userID));
    return result;
  }
}
