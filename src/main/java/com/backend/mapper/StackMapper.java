package com.backend.mapper;

import com.backend.domain.UserStacks;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StackMapper {
    List<UserStacks> findStackByUserID(@Param("user_id") int userId);
}
