package com.backend.service.stack;

import com.backend.domain.TechStacksVO;
import com.backend.mapper.TechStackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechStackServiceImpl implements TechStackService{
    @Autowired
    TechStackMapper techStackMapper;
    @Override
    public List<TechStacksVO> selectByCategoryNullable(String category) {
        return techStackMapper.selectByCategoryNullable(category);
    }
}
