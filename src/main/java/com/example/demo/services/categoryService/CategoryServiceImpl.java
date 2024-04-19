package com.example.demo.services.categoryService;

import com.example.demo.dto.CategoryDto;
import com.example.demo.entity.Category;
import com.example.demo.projection.CategoryProjection;
import com.example.demo.repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepo categoryRepo;
    @Override
    public void addCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        categoryRepo.save(category);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }

    @Override
    public void updateCategory(CategoryDto categoryDto, UUID categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow();
        category.setName(categoryDto.getName());
        categoryRepo.save(category);
    }

    @Override
    public void deleteCategory(UUID categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow();
        if (category.getProducts().isEmpty()) {
            categoryRepo.deleteById(categoryId);
        }
    }

    @Override
    public List<CategoryProjection> getCategoryName() {
        return categoryRepo.getCategories();

    }
}
