package com.backend.service.career;

import com.backend.domain.career.EducationHistoryRequestDTO;
import com.backend.domain.career.EducationHistoryVO;
import com.backend.domain.career.WorkExpRequestDTO;
import com.backend.domain.career.WorkExperiencesVO;
import com.backend.mapper.EducationHistoryMapper;
import com.backend.mapper.UserMapper;
import com.backend.mapper.WorkExperienceMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CareerServiceImpl implements CareerService {
  private final UserMapper userMapper;
  private final WorkExperienceMapper workExperienceMapper;
  private final EducationHistoryMapper educationHistoryMapper;
  private final ModelMapper modelMapper;

  @Override
  public void insertWorkExp(WorkExpRequestDTO dto) {
    WorkExperiencesVO vo = modelMapper.map(dto, WorkExperiencesVO.class);
    int userId = userMapper.findUserID(dto.getUsername());
    vo.setUserId(userId);
    workExperienceMapper.insertWorkExp(vo);
  }

  @Override
  public void updateWorkExp(WorkExpRequestDTO dto) {
    WorkExperiencesVO vo = modelMapper.map(dto, WorkExperiencesVO.class);
    int userId = userMapper.findUserID(dto.getUsername());
    vo.setUserId(userId);
    workExperienceMapper.updateWorkExp(vo);
  }

  @Override
  public void deleteWorkExp(int workId) {
    workExperienceMapper.deleteWorkExp(workId);
  }

  @Override
  public void insertEduHistory(EducationHistoryRequestDTO educationHistoryRequestDTO) {
    int userId = userMapper.findUserID(educationHistoryRequestDTO.getUsername());
    EducationHistoryVO evo = modelMapper.map(educationHistoryRequestDTO, EducationHistoryVO.class);
    evo.setUserId(userId);
    educationHistoryMapper.insertEduHistory(evo);
  }

  @Override
  public void updateEduHistory(EducationHistoryRequestDTO educationHistoryRequestDTO) {
    int userId = userMapper.findUserID(educationHistoryRequestDTO.getUsername());
    EducationHistoryVO evo = modelMapper.map(educationHistoryRequestDTO, EducationHistoryVO.class);
    evo.setUserId(userId);
    educationHistoryMapper.updateEduHistory(evo);
  }

  @Override
  public void deleteEduHistory(int eduId) {
    educationHistoryMapper.deleteEduHistory(eduId);
  }
}
