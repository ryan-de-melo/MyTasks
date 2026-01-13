package com.melo.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.melo.backend.entity.User;
import com.melo.backend.entity.UserDetailsImpl;
import com.melo.backend.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User usr = repo.findByEmail(email).orElseThrow(
            () -> new RuntimeException("Not found")
        );
        return new UserDetailsImpl(usr);
    }

}
