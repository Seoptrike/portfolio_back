package com.backend.service.work;

import com.backend.domain.career.WorkExperiencesVO;
import com.backend.domain.work.WorkDetailsVO;
import com.backend.mapper.UserMapper;
import com.backend.mapper.WorkDetailMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkServiceImpl implements WorkService {

  private final WorkDetailMapper workDetailMapper;

  @Autowired UserMapper userMapper;

  @Override
  @Transactional(readOnly = true)
  public List<WorkExperiencesVO> getWorkDetailsByUsername(String username) {
    int userId = userMapper.findUserID(username);
    return workDetailMapper.findWorkDetailsByUserId(userId);
  }

  @Override
  @Transactional
  public void addWorkDetail(WorkDetailsVO workDetailsVO) {
    // TODO: 필요 시 유효성 검증 (workId, title 등)
    workDetailMapper.insertWorkDetail(workDetailsVO);
  }

  @Override
  @Transactional
  public void updateWorkDetail(WorkDetailsVO workDetailsVO) {
    workDetailMapper.updateWorkDetail(workDetailsVO);
  }

  @Override
  @Transactional
  public void removeWorkDetail(int detailId) {
    workDetailMapper.deleteWorkDetail(detailId);
  }
}
