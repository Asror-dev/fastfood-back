package com.example.demo.controller;

import com.example.demo.dto.CategoryDto;
import com.example.demo.services.categoryService.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody CategoryDto categoryDto){
        try {
            categoryService.addCategory(categoryDto);
            return ResponseEntity.ok("added category successfully");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/get")
    public ResponseEntity<?> getAllCategory(){
        try {
            return ResponseEntity.ok(categoryService.getAllCategory());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
