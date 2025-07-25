package com.backend.service.stack;

import com.backend.domain.TechCategoriesVO;

import java.util.List;

public interface TechCategoryService {
    List<TechCategoriesVO> getAllCategories();

    void addCategory(String name);

    void updateCategory(TechCategoriesVO category);

    void deleteCategory(int categoryId);
}
