package com.example.demo.security.filter;



import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.security.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
@Configuration
@RequiredArgsConstructor

public class Filter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepo userRepo;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("key");
        if(authorization!=null){
            Jws<Claims> claimsJws = jwtService.extractJwt(authorization);
            Claims user = claimsJws.getPayload();
            String id = user.getSubject();
            User user1 = userRepo.findById(UUID.fromString(id)).get();
            UsernamePasswordAuthenticationToken usn = new UsernamePasswordAuthenticationToken(
                    user1.getPhone(),
                    user1.getPassword(),
                    user1.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(usn);
        }
        filterChain.doFilter(request,response);
    }
}
