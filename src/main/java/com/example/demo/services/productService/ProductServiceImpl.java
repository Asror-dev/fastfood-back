package com.example.demo.services.productService;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.projection.ProductProjection;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;


    @Override
    public void addProduct(ProductDto productDto, UUID categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow();
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        Product product1 = productRepo.save(product);


        category.getProducts().add(product1);
        categoryRepo.save(category);

    }

    @Override
    public List<Product> getAllProduct() {

        return productRepo.findAll();
    }

    @Override
    public void deleteProduct(UUID productId) {
        productRepo.deleteById(productId);
    }

    @Override
    public void updateProduct(ProductDto productDto, UUID productId) {
        Product product = productRepo.findById(productId).orElseThrow();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        productRepo.save(product);
    }


}
