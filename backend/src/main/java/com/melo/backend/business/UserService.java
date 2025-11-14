package com.melo.backend.business;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melo.backend.dto.UserRegisterDTO;
import com.melo.backend.dto.UserUpdateDTO;
import com.melo.backend.infrastructure.model.User;
import com.melo.backend.infrastructure.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    /**
     * Singup
     * @param dto
     * @return
     */
    public User registerUser(UserRegisterDTO dto) {
        User toRegister = new User();

        toRegister.setName(dto.name());
        toRegister.setEmail(dto.email());
        toRegister.setPassword(encoder.encode(dto.password()));

        return userRepository.save(toRegister);
    }

    /**
     * 
     * @param id
     * @return
     * @throws RuntimeException Runtime exception if user is not found.
     */
    public User getById(Long id) throws RuntimeException {
        return userRepository.findById(id).orElseThrow(
            () -> new RuntimeException("User not found")
        );
    }

    /**
     * 
     * @param id
     * @return
     * @throws RuntimeException Runtime exception if user is not found.
     */
    public User getByEmail(String email) throws RuntimeException {
        return userRepository.findByEmail(email).orElseThrow(
            () -> new RuntimeException("User not found")
        );
    }

    /**
     * 
     * @param id
     * @return 
     * @throws RuntimeException Runtime exception if user is not found.
     */
    public User deleteById(Long id) throws RuntimeException {
        User deleted = userRepository.findById(id).orElseThrow(
            () -> new RuntimeException("User not found")
        );
        userRepository.deleteById(id);
        return deleted;
    }

    /**
     * 
     * @param id
     * @param dto
     * @return
     * @throws RuntimeException Runtime exception if user is not found.
     * @throws IllegalArgumentException Illegal argument exception if email is already in use.
     */
    @Transactional
    public User updateById(Long id, UserUpdateDTO dto) throws RuntimeException, IllegalArgumentException {
        User toUpdate = userRepository.findById(id).orElseThrow(
            () -> new RuntimeException("User not found")
        );

        if (dto.name() != null && !dto.name().isEmpty()) {
            toUpdate.setName(dto.name());
        }
        if (dto.email() != null && !dto.email().isEmpty()) {
            if (userRepository.existsByEmail(dto.email())) {
                throw new IllegalArgumentException("Email already in use");
            } else {
                toUpdate.setEmail(dto.email());
            }
        }
        
        return toUpdate; // O EntityManager faz um update automaticamente
    }
}

