package com.example.demo.services.productService;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    void addProduct(UUID categoryId, MultipartFile[] file, String name, String description, Double price);


    List<Product> getAllProduct();

    void deleteProduct(UUID productId);

    void updateProduct(ProductDto productDto, UUID productId);
}
