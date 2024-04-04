package com.example.demo.services.userService;



import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.entity.User;

import java.util.Map;

public interface UserService {
    User getUserByPhone(String phone);
    Map<?,?> login(LoginDto loginDto);
    void addUser(RegisterDto registerDto);



}
