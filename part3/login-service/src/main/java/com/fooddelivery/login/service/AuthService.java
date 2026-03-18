package com.fooddelivery.login.service;

import com.fooddelivery.login.client.RegisterServiceClient;
import com.fooddelivery.login.config.JwtUtil;
import com.fooddelivery.login.dto.LoginRequest;
import com.fooddelivery.login.dto.LoginResponse;
import com.fooddelivery.login.dto.UserDto;
import com.fooddelivery.login.model.LoginAudit;
import com.fooddelivery.login.repository.LoginAuditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RegisterServiceClient registerServiceClient;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final LoginAuditRepository loginAuditRepository;

    public LoginResponse login(LoginRequest request) {
        // Fetch user from Register Service via Feign
        UserDto user = registerServiceClient.getUserByUsername(request.getUsername());

        // Verify password with BCrypt
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            saveAudit(request.getUsername(), false);
            throw new RuntimeException("Invalid password");
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(user.getUsername(), user.getEmail());

        // Save login audit to MongoDB
        saveAudit(request.getUsername(), true);

        return LoginResponse.builder()
                .token(token)
                .username(user.getUsername())
                .message("Login successful")
                .build();
    }

    private void saveAudit(String username, boolean success) {
        loginAuditRepository.save(LoginAudit.builder()
                .username(username)
                .success(success)
                .loginTime(LocalDateTime.now())
                .build());
    }
}
