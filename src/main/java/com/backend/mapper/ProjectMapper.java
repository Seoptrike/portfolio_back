package com.backend.mapper;

import com.backend.domain.project.ProjectResponseDTO;
import com.backend.domain.project.ProjectsVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectMapper {
  void insertProject(ProjectsVO vo); // useGeneratedKeys=true

  void updateProject(ProjectsVO vo);

  void deleteProject(@Param("projectId") int projectId);

  void deleteProjectStacks(@Param("projectId") int projectId);

  void insertProjectStacks(
      @Param("projectId") int projectId, @Param("stackIds") List<Integer> stackIds);

  // 조회 (JOIN + collection 매핑)
  List<ProjectResponseDTO> selectProjectsByUserId(@Param("userId") int userId);

  ProjectResponseDTO selectProjectById(@Param("projectId") int projectId);

  ProjectsVO getProjectByProjectId(@Param("projectId") int projectId);
}
