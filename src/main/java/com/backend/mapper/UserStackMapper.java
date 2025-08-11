package com.backend.mapper;

import com.backend.domain.stack.UserStacksVO;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserStackMapper {
  List<UserStacksVO> findStackByUserID(@Param("user_id") int userId);

  void insertUserStack(UserStacksVO vo);

  void updateUserStack(UserStacksVO vo);

  List<HashMap<String, Object>> findStackByUserIDWhereView(@Param("userId") int userId);

  void deleteUserStack(@Param("user_id") int userId);

  List<HashMap<String, Object>> selectUserStackByUserId(@Param("userId") int userId);
}
