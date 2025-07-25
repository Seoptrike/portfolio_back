package com.backend.mapper;

import com.backend.domain.TechCategoriesVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TechCategoryMapper {
    List<TechCategoriesVO> selectCategory();

    void insertCategory(@Param("name") String name);

    void updateCategory(TechCategoriesVO vo);

    void deleteCategory(@Param("category_id") int category_id);

}
