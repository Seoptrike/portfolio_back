package com.backend.dao;

import com.backend.domain.UsersVO;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public interface UsersDAO {
    public List<UsersVO> getUserListAll();
}
