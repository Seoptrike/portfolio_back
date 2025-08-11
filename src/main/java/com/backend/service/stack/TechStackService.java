package com.backend.service.stack;

import com.backend.domain.stack.TechStacksVO;
import java.util.List;

public interface TechStackService {
  List<TechStacksVO> selectByCategoryNullable(String category);
}
