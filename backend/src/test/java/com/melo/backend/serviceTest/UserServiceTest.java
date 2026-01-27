package com.melo.backend.serviceTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import com.melo.backend.dto.auth.AuthRegisterRequestDTO;
import com.melo.backend.dto.auth.AuthRegisterResponseDTO;
import com.melo.backend.dto.user.UserResponseDTO;
import com.melo.backend.dto.user.UserUpdateDTO;
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
    void testCreateUser() {
        AuthRegisterRequestDTO dto = new AuthRegisterRequestDTO("test", "test@email.com", "testpassword");

        User savedUser = User.builder()
                .id(Long.valueOf(0))
                .email("test@email.com")
                .name("test")
                .password("testpassword")
                .build();

        when(repo.save(any(User.class))).thenReturn(savedUser);

        AuthRegisterResponseDTO response = service.registerUser(dto);

        assertNotNull(response);
        assertEquals("test", response.name());
        assertEquals("test@email.com", response.email());
    }

    @Test
    void testGetUserById() {
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

    @Test
    void testGetUserByEmail() {
        User usr = User.builder()
                .id(Long.valueOf(0))
                .name("test")
                .email("test@email.com")
                .password("testpassword")
                .build();

        when(repo.findByEmail("test@email.com")).thenReturn(Optional.of((UserDetails) usr));

        UserResponseDTO response = service.getByEmail("test@email.com");

        assertEquals("test@email.com", response.email());
        assertEquals("test", response.name());
    }

    @Test
    void testDeleteById() {
        User usr = User.builder()
                .id(Long.valueOf(0))
                .name("test")
                .email("test@email.com")
                .password("testpassword")
                .build();

        when(repo.findById(Long.valueOf(0))).thenReturn(Optional.of(usr));
        doNothing().when(repo).deleteById(Long.valueOf(0));

        UserResponseDTO response = service.deleteById(Long.valueOf(0));

        assertEquals("test@email.com", response.email());
    }

    @Test
    void testUpdateById() {
        User usr = User.builder()
                .id(Long.valueOf(0))
                .name("test")
                .email("test@email.com")
                .password("testpassword")
                .build();

        when(repo.findById(Long.valueOf(0))).thenReturn(Optional.of(usr));
        when(repo.save(any(User.class))).thenReturn(usr);

        UserUpdateDTO dto = new UserUpdateDTO("newtest", "newtestpassword");

        service.updateById(Long.valueOf(0), dto);

        verify(repo).save(argThat(user -> user.getName().equals("newtest") &&
                user.getPassword().equals("newtestpassword")));
    }
}
