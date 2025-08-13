package com.backend.mapper;

import com.backend.domain.user.UsersVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
  List<UsersVO> getUserAllList();

  Integer findUserID(@Param("username") String username);

  List<UsersVO> searchUsername(@Param("username") String username);

  UsersVO getUserData(@Param("userId") int userId);

  void updateUserData(UsersVO vo);

  void softDeleteUserData(@Param("userId") int userId);
}
