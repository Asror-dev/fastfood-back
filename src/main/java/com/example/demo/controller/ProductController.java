package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.services.productService.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

@PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto, @RequestParam UUID categoryId) {
    try {
        productService.addProduct(productDto, categoryId);
        return ResponseEntity.ok("Product added successfully");
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
@GetMapping("/get")
    public ResponseEntity<?> getProduct(@RequestParam UUID categoryId) {
    try {
        return ResponseEntity.ok(productService.getProduct(categoryId));
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}

}
