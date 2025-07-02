package com.backend.mapper;

import com.backend.domain.ProjectsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectMapper {
    List<ProjectsVO> findProjectByUserID(@Param("user_id") int userId);
}
