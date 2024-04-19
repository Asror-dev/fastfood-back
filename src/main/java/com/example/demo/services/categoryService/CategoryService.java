package com.example.demo.services.categoryService;

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    void addCategory(CategoryDto categoryDto);

    List<Category> getAllCategory();

    void updateCategory(CategoryDto categoryDto, UUID categoryId);

    void deleteCategory(UUID categoryId);
}
