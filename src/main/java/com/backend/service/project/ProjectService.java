package com.backend.service.project;

import com.backend.domain.project.ProjectRequestDTO;
import com.backend.domain.project.ProjectResponseDTO;
import java.util.List;
import java.util.Optional;

public interface ProjectService {
  /** 프로젝트 생성 (username → userId 매핑은 구현체에서 처리) */
  void createProject(ProjectRequestDTO dto);

  /** 프로젝트 수정 */
  void updateProject(int projectId, ProjectRequestDTO dto);

  /** 프로젝트 삭제 (연관 스택도 서비스에서 정리) */
  void deleteProject(int projectId);

  /** 사용자별 프로젝트 목록 */
  List<ProjectResponseDTO> getProjectsByUsername(String username);

  /** 단건 조회 (없을 수 있으니 Optional 권장) */
  Optional<ProjectResponseDTO> findProjectById(int projectId);
}
