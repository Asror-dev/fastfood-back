package com.example.demo.services.productService;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Product;
import com.example.demo.projection.ProductProjection;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    void addProduct(ProductDto productDto, UUID categoryId);


    List<Product> getAllProduct();

    void deleteProduct(UUID productId);

    void updateProduct(ProductDto productDto, UUID productId);
}
