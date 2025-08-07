package com.backend.service.project;

import com.backend.domain.ProjectRequestDTO;
import com.backend.domain.ProjectResponseDTO;
import com.backend.domain.ProjectStackVO;
import com.backend.domain.ProjectsVO;
import com.backend.mapper.ProjectMapper;
import com.backend.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    UserMapper userMapper;

    @Transactional
    @Override
    public void createProject(ProjectRequestDTO dto) {
        ProjectsVO vo = new ProjectsVO();
        int userId = userMapper.findUserID(dto.getUsername());
        vo.setUserId(userId);
        BeanUtils.copyProperties(dto, vo); // DTO → VO 값 복사
        projectMapper.insertProject(vo); // 프로젝트 데이터를 DB에 저장하고, 자동 생성된 project_id 값을 vo 객체의 projectId 필드에 자동으로 주입한다.
        // 이 기능은 MyBatis XML의 <insert> 태그에서 useGeneratedKeys="true"와 keyProperty="projectId" 설정을 통해 동작함.
        // → 즉, DB에서 생성된 기본키(PK)를 Java 객체에 다시 세팅해주는 역할을 한다.
        if (dto.getStackIds() != null && !dto.getStackIds().isEmpty()) {
            projectMapper.insertProjectStacks(vo.getProjectId(), dto.getStackIds());
        }
    }

    @Override
    @Transactional
    public void updateProject(int projectId, ProjectRequestDTO dto) {
        ProjectsVO vo = new ProjectsVO();
        BeanUtils.copyProperties(dto, vo);
        vo.setProjectId(projectId);
        projectMapper.updateProject(vo);
        projectMapper.deleteProjectStacks(projectId);
        if (dto.getStackIds() != null && !dto.getStackIds().isEmpty()) {
            projectMapper.insertProjectStacks(projectId, dto.getStackIds());
        }
    }

    @Override
    @Transactional
    public void deleteProject(int projectId) {
        projectMapper.deleteProjectStacks(projectId);
        projectMapper.deleteProject(projectId);
    }

    @Override
    public List<ProjectResponseDTO> getProjectsByUserId(String username) {
        int userId = userMapper.findUserID(username);
        List<ProjectResponseDTO> list = projectMapper.getProjectsByUserId(userId);
        return list != null ? list : new ArrayList<>();
    }

    @Override
    public ProjectResponseDTO getProjectById(int projectId) {
        return projectMapper.getProjectById(projectId);
    }

    @Override
    public List<HashMap<String, Object>> selectAllProjectsByUsername(String username) {
        int userId = userMapper.findUserID(username);
        List<HashMap<String, Object>> list = projectMapper.selectAllProjectsByUserId(userId);
        for (Map<String, Object> project : list) {
            Object stackNames = project.get("stack_names");
            if (stackNames != null && stackNames instanceof String) {
                String[] stackArray = ((String) stackNames).split("\\s*,\\s*"); // 쉼표+공백 기준 분리
                project.put("stack_names", Arrays.asList(stackArray)); // 배열로 덮어쓰기
            }
        }
        return list != null ? list : new ArrayList<>();
    }

    @Override
    public HashMap<String,Object> selectProjectByProjectId(int projectId) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("project", projectMapper.selectProjectByPjId(projectId));
        map.put("stacks", projectMapper.selectStackByProjectId(projectId));
        return map;
    }
}
