package com.example.demo.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
public class ProductDto {

    private String name;
    private Double price;
    private String description;
    private MultipartFile[] multipartFile;
    private UUID categoryId;

}
