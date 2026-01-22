package com.melo.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.melo.backend.dto.auth.AuthRegisterRequestDTO;
import com.melo.backend.dto.user.UserResponseDTO;
import com.melo.backend.dto.user.UserUpdateDTO;
import com.melo.backend.service.UserService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Void> registerUser(@RequestBody AuthRegisterRequestDTO dto) {
        userService.registerUser(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUserById(@PathVariable Long id, @RequestBody UserUpdateDTO dto) {
        userService.updateById(id, dto);
        return ResponseEntity.noContent().build();
    }

    /**
     * Update parcial
     */
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> partialUpdateUserById(@PathVariable Long id,
            @RequestBody UserUpdateDTO dto) {
        return ResponseEntity.ok(userService.partialUpdateById(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

}
