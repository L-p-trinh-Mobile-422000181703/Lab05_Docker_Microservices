package com.fooddelivery.register.controller;

import com.fooddelivery.register.dto.RegisterRequest;
import com.fooddelivery.register.dto.RegisterResponse;
import com.fooddelivery.register.dto.UserDto;
import com.fooddelivery.register.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        RegisterResponse response = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Internal endpoint - called by Login Service via Feign
    @GetMapping("/users/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        UserDto user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }
}
