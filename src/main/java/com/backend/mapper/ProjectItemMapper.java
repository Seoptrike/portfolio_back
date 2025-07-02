package com.backend.mapper;

import com.backend.domain.ProjectItemsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectItemMapper {
    List<ProjectItemsVO> findProjectDetail(@Param("project_id") int projectId);
}
