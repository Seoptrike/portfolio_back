package com.backend.mapper;

import com.backend.domain.UserStacksVO;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface UserStackMapper {
    List<UserStacksVO> findStackByUserID(@Param("user_id") int userId);

    void insertUserStack(UserStacksVO vo);

    void updateUserStack(UserStacksVO vo);

    List<HashMap<String,Object>> findStackByUserIDWhereView(@Param("user_id") int userId);

    void deleteUserStack(@Param("user_id") int userId);
}
