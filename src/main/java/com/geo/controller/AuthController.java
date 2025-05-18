package com.geo.controller;

import com.geo.dto.RegisterRequest;
import com.geo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.geo.dto.LoginRequest;
import com.geo.entity.WebsiteUser;
import com.geo.repository.WebsiteUserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/v2/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthService authService;
    private final WebsiteUserRepository userRepository;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        Optional<WebsiteUser> userOpt = userRepository.findByUsername(request.getUsername());

        if (userOpt.isEmpty()) {
            return "Пользователь не найден";
        }

        WebsiteUser user = userOpt.get();

        if (!user.getPassword().equals(request.getPassword())) {
            return "Неверный пароль";
        }

        return "Успешный вход";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            authService.register(request);
            return ResponseEntity.ok("User registered successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
