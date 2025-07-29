package com.backend.mapper;

import com.backend.domain.ProjectResponseDTO;
import com.backend.domain.ProjectsVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;

public interface ProjectMapper {
    List<ProjectsVO> findProjectByUserID(@Param("user_id") int userId);

    void insertProject(@RequestBody ProjectsVO vo);

    void insertProjectStacks(@Param("projectId") int projectId, @Param("stackIds") List<Integer> stackIds);

    void updateProject(@RequestBody ProjectsVO vo);

    void deleteProject(@Param("project_id") int projectId);

    List<ProjectResponseDTO> getProjectsByUserId(@Param("user_id") int userId);

    void deleteProjectStacks(@Param("project_id") int projectId);

    ProjectResponseDTO getProjectById(@Param("project_id") int projectId);

    List<HashMap<String,Object>> selectAllProjectsByUserId(@Param("userId") int userId);
}
