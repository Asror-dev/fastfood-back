package com.example.demo.services.productService;

import com.example.demo.dto.ProductDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.Image;
import com.example.demo.entity.Product;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.repository.ImageRepo;
import com.example.demo.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final ImageRepo imageRepo;


    @Override
    public void addProduct(UUID categoryId, MultipartFile[] files, String name, String description, Double price) {

        Category category = categoryRepo.findById(categoryId).orElseThrow();
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);

        List<Image> images = new ArrayList<>();
        String uploadDir = "G:/fastfood/demo/uploads/"; // Fayl manzili

        for (MultipartFile file : files) {
            // Fayl nomi unikal bo'lishi uchun UUID orqali generatsiya qilinadi
            String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            String filePath = uploadDir + uniqueFileName; // To'liq fayl manzili

            // Faylni saqlash
            try {
                // Faylni yaratish
                File uploadFile = new File(filePath);
                file.transferTo(uploadFile);

                // Rasm obyektini yaratish va ma'lumotlar bazasiga saqlash
                Image image = new Image();
                image.setName(uniqueFileName); // Fayl nomi
                image.setPath(filePath); // Fayl manzili
                Image savedImage = imageRepo.save(image); // Ma'lumotlar bazasiga saqlash
                images.add(savedImage); // Rasm obyektini ro'yxatga qo'shish
            } catch (IOException e) {
                throw new RuntimeException("Failed to store file " + uniqueFileName, e);
            }
        }
        product.setImages(images);
        // Product obyektini ma'lumotlar bazasiga saqlash
        Product savedProduct = productRepo.save(product);

        // Category obyektiga bog'liq bo'lgan Product obyektini qo'shish
        category.getProducts().add(savedProduct);
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
