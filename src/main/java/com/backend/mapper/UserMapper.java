package com.backend.mapper;

import com.backend.domain.user.UsersVO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
  List<UsersVO> getUserAllList();

  Integer findUserID(@Param("username") String username);
}
