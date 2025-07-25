package com.backend.service.users;

import com.backend.domain.UsersVO;

import java.util.HashMap;
import java.util.List;

public interface UserService {
    List<UsersVO> getUserAllList();

    int findUserID(String username);

    HashMap<String, Object> getUserTotalData(String username);
}
