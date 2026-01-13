package com.melo.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.melo.backend.entity.User;
import com.melo.backend.repository.UserRepository;

@Component
public class AuthenticatedUserService {

    @Autowired
    private UserRepository repo;

    public User get() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        return repo.findByEmail(email).orElseThrow(() -> new RuntimeException("Not found"));
    }
}
