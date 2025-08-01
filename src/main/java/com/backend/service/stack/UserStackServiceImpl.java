package com.backend.service.stack;

import com.backend.domain.StackWithScore;
import com.backend.domain.UserStackRequestDTO;
import com.backend.domain.UserStacksVO;
import com.backend.mapper.UserMapper;
import com.backend.mapper.UserStackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class UserStackServiceImpl implements UserStackService {
    @Autowired
    UserStackMapper userStackMapper;

    @Autowired
    UserMapper userMapper;

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
            vo.setUser_id(userId);
            vo.setStack_id(stacks.getStackId());
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
            vo.setUser_id(userId);
            vo.setStack_id(stacks.getStackId());
            vo.setScore(stacks.getScore());
            userStackMapper.insertUserStack(vo);
        }
    }

    @Override
    public List<HashMap<String, Object>> selectUserStackByUserId(String username) {
        int userId = userMapper.findUserID(username);
        return userStackMapper.selectUserStackByUserId(userId);
    }
}
