package com.backend.service.stack;

import com.backend.domain.stack.TechCategoriesVO;
import com.backend.mapper.TechCategoryMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TechCategoryServiceImpl implements TechCategoryService {

  @Autowired TechCategoryMapper techCategoryMapper;

  @Override
  public List<TechCategoriesVO> getAllCategories() {
    return techCategoryMapper.selectCategory();
  }

  @Override
  public void addCategory(String name) {
    techCategoryMapper.insertCategory(name);
  }

  @Override
  public void updateCategory(TechCategoriesVO category) {
    techCategoryMapper.updateCategory(category);
  }

  @Override
  public void deleteCategory(int categoryId) {
    techCategoryMapper.deleteCategory(categoryId);
  }
}
