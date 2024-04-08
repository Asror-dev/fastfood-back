package com.example.demo.services.categoryService;

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Category;

import java.util.List;

public interface CategoryService {
    void addCategory(CategoryDto categoryDto);

    List<Category> getAllCategory();
}
