package com.backend.service.stack;

import com.backend.domain.stack.TechStacksVO;
import com.backend.mapper.TechStackMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TechStackServiceImpl implements TechStackService {
  @Autowired TechStackMapper techStackMapper;

  @Override
  public List<TechStacksVO> selectByCategoryNullable(String category) {
    return techStackMapper.selectByCategoryNullable(category);
  }
}
