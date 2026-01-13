package com.melo.backend.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.melo.backend.entity.User;
import com.melo.backend.entity.UserDetailsImpl;

@Component
public class AuthenticatedUserService {

    public User get() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl principal = (UserDetailsImpl) auth.getPrincipal();
        return principal.getUser();
    }
}
