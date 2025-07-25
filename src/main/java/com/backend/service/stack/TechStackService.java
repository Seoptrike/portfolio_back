package com.backend.service.stack;

import com.backend.domain.TechStacksVO;

import java.util.List;

public interface TechStackService {
    List<TechStacksVO> selectByCategoryNullable(String category);
}
