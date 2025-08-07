package com.backend.controller;

import com.backend.domain.ProjectRequestDTO;
import com.backend.domain.ProjectResponseDTO;
import com.backend.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @PostMapping
    public ResponseEntity<Void> createProject(@RequestBody ProjectRequestDTO dto) {
        projectService.createProject(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{username}") //유저 프로젝트 검색
    public ResponseEntity<List<HashMap<String, Object>>> selectAllProjectsByUsername(@PathVariable String username) {
        return ResponseEntity.ok(projectService.selectAllProjectsByUsername(username));
    }

    //특정 프로젝트 검색
    @GetMapping("/{projectId}")
    public ResponseEntity<HashMap<String, Object>> getProjectById(@PathVariable int projectId) {
        return ResponseEntity.ok(projectService.selectProjectByProjectId(projectId));
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<Void> updateProject(@PathVariable int projectId, @RequestBody ProjectRequestDTO dto) {
        projectService.updateProject(projectId, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable int projectId) {
        projectService.deleteProject(projectId);
        return ResponseEntity.ok().build();
    }
}
