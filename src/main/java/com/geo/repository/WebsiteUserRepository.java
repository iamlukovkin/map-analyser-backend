package com.geo.repository;

import com.geo.entity.WebsiteUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WebsiteUserRepository extends JpaRepository<WebsiteUser, Long> {
    Optional<WebsiteUser> findByEmail(String email);
    Optional<WebsiteUser> findByUsername(String username);
}
