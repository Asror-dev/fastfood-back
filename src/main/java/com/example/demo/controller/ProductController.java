package com.example.demo.controller;

import com.example.demo.dto.ProductDto;
import com.example.demo.services.productService.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add")
        public ResponseEntity<?> addProduct(@RequestParam UUID categoryId, @RequestParam(required = false)MultipartFile[] file, @RequestParam String name,@RequestParam String description,@RequestParam Double price) {
        try {
            productService.addProduct(categoryId,file,name,description,price);
            return ResponseEntity.ok("Product added successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllProduct(){
        try {
            return ResponseEntity.ok(productService.getAllProduct());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteProduct(@RequestParam UUID productId) {
        try {
            productService.deleteProduct(productId);
            return ResponseEntity.ok("Product deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDto productDto, @RequestParam UUID productId) {
        try {
            productService.updateProduct(productDto, productId);
            return ResponseEntity.ok("Product updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
