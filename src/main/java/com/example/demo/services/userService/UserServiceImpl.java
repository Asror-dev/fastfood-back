package com.example.demo.services.userService;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegisterDto;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;



    @Override
    public User getUserByPhone(String phone) {
        Optional<User> byPhone = userRepo.findByPhone(phone);
        return byPhone.orElseThrow();
    }

    @Override
    public Map<?, ?> login(LoginDto loginDto) {
        User user = userRepo.findByPhone(loginDto.getPhone()).orElseThrow();
        UUID id = user.getId();
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getPhone(),
                        loginDto.getPassword()
                )
        );
        String jwt = jwtService.generateJwt(id.toString());
        String refreshJwt = jwtService.generateRefreshJwt(id.toString());
        return Map.of("access_token", jwt, "refresh_token", refreshJwt);
    }

    @Override
    public void addUser(RegisterDto registerDto) {
        Role roleUser = roleRepo.findByName("ROLE_USER").orElseThrow();
        User user = new User();
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setGender(registerDto.getGender());
        user.setPhone(registerDto.getPhone());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRoles(List.of(roleUser));
        userRepo.save(user);

    }


}
