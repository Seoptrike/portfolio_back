package com.backend.mapper;

import com.backend.domain.stack.TechCategoriesVO;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TechCategoryMapper {
  List<TechCategoriesVO> selectCategory();

  void insertCategory(@Param("name") String name);

  void updateCategory(TechCategoriesVO vo);

  void deleteCategory(@Param("category_id") int category_id);
}
