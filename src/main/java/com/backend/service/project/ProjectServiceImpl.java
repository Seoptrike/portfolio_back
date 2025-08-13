package com.backend.service.project;

import com.backend.domain.project.ProjectRequestDTO;
import com.backend.domain.project.ProjectResponseDTO;
import com.backend.domain.project.ProjectsVO;
import com.backend.mapper.ProjectMapper;
import com.backend.mapper.UserMapper;

import java.util.*;

import com.backend.service.auth.ImageKitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectMapper projectMapper;
    private final UserMapper userMapper;
    private final ImageKitService imageKitService;

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
        ProjectsVO before = projectMapper.getProjectByProjectId(projectId);
        String oldFileId = before != null ? before.getThumbnailFileId() : null;

        ProjectsVO vo = new ProjectsVO();
        BeanUtils.copyProperties(dto, vo, "userId"); // userId는 변경 안 함
        vo.setProjectId(projectId);
        projectMapper.updateProject(vo);
        projectMapper.deleteProjectStacks(projectId);
        if (dto.getStackIds() != null && !dto.getStackIds().isEmpty()) {
            projectMapper.insertProjectStacks(projectId, dto.getStackIds());
        }

        String newFileId = dto.getThumbnailFileId();
        // (케이스)
        // - 새로 업로드됨: old != null && new != null && !old.equals(new) → old 삭제
        // - 썸네일 제거됨: old != null && new == null → old 삭제
        boolean shouldDeleteOld =
                oldFileId != null && !oldFileId.equals(newFileId);

        if (shouldDeleteOld) {
            // 4) 트랜잭션 커밋 이후에 삭제 실행
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    imageKitService.deleteFileSafe(oldFileId);
                }
            });
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
