package com.backend.mapper;

import com.backend.domain.stack.TechStacksVO;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TechStackMapper {
  List<TechStacksVO> selectByCategoryNullable(@Param("category") String category);
}
