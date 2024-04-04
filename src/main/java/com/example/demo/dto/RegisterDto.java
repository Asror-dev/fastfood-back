package com.example.demo.dto;

import com.example.demo.entity.enums.Gender;
import lombok.Data;


@Data
public class RegisterDto {
    private String firstName;
    private String lastName;
    private String phone;
    private String password;
    private Gender gender;

}
