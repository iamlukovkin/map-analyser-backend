package com.geo.service;

import com.geo.dto.RegisterRequest;
import com.geo.entity.WebsiteUser;
import com.geo.repository.WebsiteUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final WebsiteUserRepository userRepository;

    public void register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Username already in use");
        }

        WebsiteUser user = WebsiteUser.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .password(request.getPassword())
                .registerDate(LocalDateTime.now())
                .build();

        userRepository.save(user);
    }
}
