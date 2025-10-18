package com.backend.controller;

import com.backend.domain.project.ProjectRequestDTO;
import com.backend.domain.project.ProjectResponseDTO;
import com.backend.service.project.ProjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
  @Autowired ProjectService projectService;

  @PostMapping
  public ResponseEntity<Void> createProject(@RequestBody ProjectRequestDTO dto) {
    projectService.createProject(dto);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{username}") // 유저 프로젝트 검색
  public ResponseEntity<List<ProjectResponseDTO>> selectAllProjectsByUsername(
      @PathVariable String username) {
    return ResponseEntity.ok(projectService.getProjectsByUsername(username));
  }

  @PutMapping("/{projectId}")
  public ResponseEntity<Void> updateProject(
      @PathVariable int projectId, @RequestBody ProjectRequestDTO dto) {
    projectService.updateProject(projectId, dto);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{projectId}")
  public ResponseEntity<Void> deleteProject(@PathVariable int projectId) {
    projectService.deleteProject(projectId);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/detail/{projectId}")
  public ResponseEntity<ProjectResponseDTO> selectProjectById(@PathVariable int projectId) {
    return projectService
        .findProjectById(projectId)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}
