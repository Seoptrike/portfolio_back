package com.backend.mapper;

import com.backend.domain.TechStacksVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TechStackMapper {
    List<TechStacksVO> selectByCategoryNullable(@Param("category") String category);
}
