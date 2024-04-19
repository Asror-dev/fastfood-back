package com.example.demo.controller;

import com.example.demo.dto.CategoryDto;
import com.example.demo.projection.CategoryProjection;
import com.example.demo.services.categoryService.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody CategoryDto categoryDto) {
        try {
            categoryService.addCategory(categoryDto);
            return ResponseEntity.ok("added category successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/products")
    public ResponseEntity<?> getAllCategory() {
        try {
            return ResponseEntity.ok(categoryService.getAllCategory());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryDto categoryDto, @RequestParam UUID categoryId) {
        try {
            categoryService.updateCategory(categoryDto, categoryId);
            return ResponseEntity.ok("updated category successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
        @DeleteMapping("/delete")
        public ResponseEntity<?> deleteCategory (@RequestParam UUID categoryId){
            try {
                categoryService.deleteCategory(categoryId);
                return ResponseEntity.ok("deleted category successfully");
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @GetMapping("/get")
        public ResponseEntity<?> getCategory() {
        try {
            List<CategoryProjection> categoryName = categoryService.getCategoryName();
            return ResponseEntity.ok(categoryName);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        }


    }

