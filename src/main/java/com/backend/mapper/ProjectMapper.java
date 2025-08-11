package com.backend.mapper;

import com.backend.domain.project.ProjectResponseDTO;
import com.backend.domain.project.ProjectsVO;
import com.backend.domain.stack.TechStacksVO;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

@Mapper
public interface ProjectMapper {
  List<ProjectsVO> findProjectByUserID(@Param("user_id") int userId);

  void insertProject(@RequestBody ProjectsVO vo);

  void insertProjectStacks(
      @Param("projectId") int projectId, @Param("stackIds") List<Integer> stackIds);

  void updateProject(@RequestBody ProjectsVO vo);

  void deleteProject(@Param("projectId") int projectId);

  List<ProjectResponseDTO> getProjectsByUserId(@Param("userId") int userId);

  void deleteProjectStacks(@Param("projectId") int projectId);

  ProjectResponseDTO getProjectById(@Param("projectId") int projectId);

  List<HashMap<String, Object>> selectAllProjectsByUserId(@Param("userId") int userId);

  ProjectResponseDTO selectProjectByPjId(@Param("projectId") int projectId);

  List<TechStacksVO> selectStackByProjectId(@Param("projectId") int projectId);
}
