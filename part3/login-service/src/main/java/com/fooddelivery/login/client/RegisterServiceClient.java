package com.fooddelivery.login.client;

import com.fooddelivery.login.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "register-service", url = "${register-service.url}")
public interface RegisterServiceClient {

    @GetMapping("/api/users/{username}")
    UserDto getUserByUsername(@PathVariable("username") String username);
}
