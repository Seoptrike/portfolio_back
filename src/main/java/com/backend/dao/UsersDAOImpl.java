package com.backend.dao;

import com.backend.domain.UsersVO;
import org.apache.catalina.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Repository
public class UsersDAOImpl implements UsersDAO {
    @Autowired
    SqlSession session;
    String namespace = "com.backend.mapper.UsersMapper";

    @Override
    public List<UsersVO> getUserListAll() {
        return session.selectList(namespace + ".getUserListAll");
    }
}
