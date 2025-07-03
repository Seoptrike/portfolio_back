package com.backend.mapper;

import com.backend.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;


@Mapper
public interface UserMapper {
    List<UsersVO> getUserAllList();

    Integer findUserID(@Param("username") String username);
}

