package com.backend.service.users;

import com.backend.domain.UsersVO;
import com.backend.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UsersVO> getUserAllList() {
        return userMapper.getUserAllList();
    }

    public int findUserID(String username){
        return userMapper.findUserID(username);
    }
}
