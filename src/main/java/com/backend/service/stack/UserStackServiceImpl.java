package com.backend.service.stack;

import com.backend.domain.stack.StackWithScore;
import com.backend.domain.stack.UserStackRequestDTO;
import com.backend.domain.stack.UserStackResponseDTO;
import com.backend.domain.stack.UserStacksVO;
import com.backend.mapper.UserMapper;
import com.backend.mapper.UserStackMapper;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserStackServiceImpl implements UserStackService {
  @Autowired UserStackMapper userStackMapper;

  @Autowired UserMapper userMapper;

  @Override
  public List<UserStacksVO> findStackByUserID(int userID) {
    return userStackMapper.findStackByUserID(userID);
  }

  @Transactional
  @Override
  public void insertUserStack(UserStackRequestDTO dto) {
    int userId = dto.getUserId();
    List<StackWithScore> stackList = dto.getStackList();
    for (StackWithScore stacks : stackList) {
      UserStacksVO vo = new UserStacksVO();
      vo.setUserId(userId);
      vo.setStackId(stacks.getStackId());
      vo.setScore(stacks.getScore());
      userStackMapper.insertUserStack(vo);
    }
  }

  @Override
  public void updateUserStack(UserStackRequestDTO dto) {
    int userId = dto.getUserId();
    userStackMapper.deleteUserStack(userId);
    List<StackWithScore> stackList = dto.getStackList();
    for (StackWithScore stacks : stackList) {
      UserStacksVO vo = new UserStacksVO();
      vo.setUserId(userId);
      vo.setStackId(stacks.getStackId());
      vo.setScore(stacks.getScore());
      userStackMapper.insertUserStack(vo);
    }
  }

  @Override
  public List<UserStackResponseDTO> selectUserStackByUserId(String username) {
    int userId = userMapper.findUserID(username);
    return userStackMapper.selectUserStackByUserId(userId);
  }
}
