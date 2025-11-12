package com.melo.backend.business;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.melo.backend.infrastructure.RegisterDTO;
import com.melo.backend.infrastructure.model.User;
import com.melo.backend.infrastructure.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public User registerUser(RegisterDTO data) {
        User userToRegister = new User();

        userToRegister.setName(data.getName());
        userToRegister.setEmail(data.getEmail());
        userToRegister.setPassword(encoder.encode(data.getPassword()));

        return userRepository.save(userToRegister);
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User deleteByEmail(Long id) {
        User deleted = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.deleteById(id);
        return deleted;
    }
}
