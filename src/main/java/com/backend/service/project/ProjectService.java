package com.backend.service.project;

import com.backend.domain.ProjectRequestDTO;
import com.backend.domain.ProjectResponseDTO;

import java.util.HashMap;
import java.util.List;

public interface ProjectService {
    void createProject(ProjectRequestDTO dto);

    void updateProject(int projectId, ProjectRequestDTO dto);

    void deleteProject(int projectId);

    List<ProjectResponseDTO> getProjectsByUserId(String username);

    ProjectResponseDTO getProjectById(int projectId);

    List<HashMap<String, Object>> selectAllProjectsByUsername(String username);
}
