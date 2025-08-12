package com.backend.service.project;

import com.backend.domain.project.ProjectRequestDTO;
import com.backend.domain.project.ProjectResponseDTO;
import com.backend.domain.project.ProjectsVO;
import com.backend.mapper.ProjectMapper;
import com.backend.mapper.UserMapper;
import java.util.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectServiceImpl implements ProjectService {

  @Autowired ProjectMapper projectMapper;

  @Autowired UserMapper userMapper;

  @Transactional
  @Override
  public void createProject(ProjectRequestDTO dto) {
    ProjectsVO vo = new ProjectsVO();
    vo.setUserId(userMapper.findUserID(dto.getUsername())); // username → userId
    // 주의: DTO와 VO 필드명이 겹칠 때만 복사됨
    BeanUtils.copyProperties(dto, vo, "userId"); // 이미 userId 세팅했으니 제외 가능
    projectMapper.insertProject(vo);

    if (dto.getStackIds() != null && !dto.getStackIds().isEmpty()) {
      projectMapper.insertProjectStacks(vo.getProjectId(), dto.getStackIds());
    }
  }

  @Transactional
  @Override
  public void updateProject(int projectId, ProjectRequestDTO dto) {
    ProjectsVO vo = new ProjectsVO();
    BeanUtils.copyProperties(dto, vo, "userId"); // userId는 변경 안 함
    vo.setProjectId(projectId);
    projectMapper.updateProject(vo);

    projectMapper.deleteProjectStacks(projectId);
    if (dto.getStackIds() != null && !dto.getStackIds().isEmpty()) {
      projectMapper.insertProjectStacks(projectId, dto.getStackIds());
    }
  }

  @Transactional
  @Override
  public void deleteProject(int projectId) {
    projectMapper.deleteProjectStacks(projectId);
    projectMapper.deleteProject(projectId);
  }

  @Override
  public List<ProjectResponseDTO> getProjectsByUsername(String username) {
    int userId = userMapper.findUserID(username);
    return projectMapper.selectProjectsByUserId(userId);
  }

  @Override
  public Optional<ProjectResponseDTO> findProjectById(int projectId) {
    return Optional.ofNullable(projectMapper.selectProjectById(projectId));
  }
}
