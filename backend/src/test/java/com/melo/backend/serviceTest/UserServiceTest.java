package com.melo.backend.serviceTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.melo.backend.dto.user.UserRegisterDTO;
import com.melo.backend.dto.user.UserResponseDTO;
import com.melo.backend.entity.User;
import com.melo.backend.repository.UserRepository;
import com.melo.backend.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository repo;

    @InjectMocks
    private UserService service;

    @Test
    void createUser() {
        UserRegisterDTO dto = new UserRegisterDTO("test", "test@email.com", "testpassword");

        User savedUser = User.builder()
                .id(Long.valueOf(0))
                .email("test@email.com")
                .name("test")
                .password("testpassword")
                .build();

        when(repo.save(any(User.class))).thenReturn(savedUser);

        UserResponseDTO response = service.registerUser(dto);

        assertNotNull(response);
        assertEquals("test", response.name());
        assertEquals("test@email.com", response.email());
    }

    @Test
    void getUser() {
        User usr = User.builder()
                .id(Long.valueOf(0))
                .email("test@email.com")
                .name("test")
                .password("testpassword")
                .build();

        when(repo.findById(Long.valueOf(0))).thenReturn(Optional.of(usr));

        UserResponseDTO response = service.getById(Long.valueOf(0));

        assertEquals("test@email.com", response.email());
    }
}
