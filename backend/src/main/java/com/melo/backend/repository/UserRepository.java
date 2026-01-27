package com.melo.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.melo.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<UserDetails> findByEmail(String email);
}
