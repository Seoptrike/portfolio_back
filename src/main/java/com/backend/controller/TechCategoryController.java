package com.backend.controller;

import com.backend.domain.TechCategoriesVO;
import com.backend.mapper.TechCategoryMapper;
import com.backend.service.stack.TechCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tech-categories")
public class TechCategoryController {
    @Autowired
    TechCategoryMapper techCategoryMapper;

    @Autowired
    TechCategoryService techCategoryService;
    @GetMapping
    public List<TechCategoriesVO> selectCategory() {
        return techCategoryService.getAllCategories();
    }

    @PostMapping
    public void insertCategory(@RequestBody TechCategoriesVO TCvo) {
        techCategoryService.addCategory(TCvo.getName());
    }

    @PutMapping
    public void updateCategory(@RequestBody TechCategoriesVO TCvo) {
        techCategoryService.updateCategory(TCvo);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable int categoryId) {
        techCategoryService.deleteCategory(categoryId);
    }
}
