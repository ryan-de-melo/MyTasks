package com.melo.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melo.backend.dto.user.UserRegisterDTO;
import com.melo.backend.dto.user.UserResponseDTO;
import com.melo.backend.dto.user.UserUpdateDTO;
import com.melo.backend.entity.User;
import com.melo.backend.mappers.UserMapper;
import com.melo.backend.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Singup
     * 
     * @param dto
     * @return
     */
    public UserResponseDTO registerUser(UserRegisterDTO dto) {
        User toRegister = new User();

        toRegister.setName(dto.name());
        toRegister.setEmail(dto.email());
        // toRegister.setPassword(encoder.encode(dto.password()));
        toRegister.setPassword(dto.password());

        return UserMapper.toResponse(userRepository.save(toRegister));
    }

    /**
     * 
     * @param id
     * @return
     * @throws RuntimeException Runtime exception if user is not found.
     */
    public UserResponseDTO getById(Long id) throws RuntimeException {
        return UserMapper.toResponse(userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found")));
    }

    /**
     * 
     * @param id
     * @return
     * @throws RuntimeException Runtime exception if user is not found.
     */
    public UserResponseDTO getByEmail(String email) throws RuntimeException {
        return UserMapper.toResponse(userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("User not found")));
    }

    /**
     * 
     * @param id
     * @return
     * @throws RuntimeException Runtime exception if user is not found.
     */
    public UserResponseDTO deleteById(Long id) throws RuntimeException {
        User deleted = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found"));
        userRepository.deleteById(id);
        return UserMapper.toResponse(deleted);
    }

    public void updateById(Long id, UserUpdateDTO dto) {
        Optional<User> opt = userRepository.findById(id);
        if (opt.isPresent()) {
            User user = opt.get();
            User updatedUser = User.builder()
                    .id(user.getId())
                    .name(dto.name())
                    .password(dto.password())
                    .build();

            userRepository.save(updatedUser);
        }
    }

    /**
     * 
     * @param id
     * @param dto
     * @return
     * @throws RuntimeException         Runtime exception if user is not found.
     * @throws IllegalArgumentException Illegal argument exception if email is
     *                                  already in use.
     */
    @Transactional
    public UserResponseDTO partialUpdateById(Long id, UserUpdateDTO dto)
            throws RuntimeException, IllegalArgumentException {
        User toUpdate = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found"));

        if (dto.name() != null && !dto.name().isEmpty()) {
            toUpdate.setName(dto.name());
        }
        if (dto.password() != null && !dto.password().isEmpty()) {
            toUpdate.setEmail(dto.password());
        }

        return UserMapper.toResponse(toUpdate); // O EntityManager faz um update automaticamente
    }

    /**
     * Method to return an entity of user.
     * This is used only to "convert" dto into a user
     * 
     * @param id user id
     * @return user entity
     */
    protected User getUserEntityById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found"));
    }

    public List<UserResponseDTO> getAll() {
        return userRepository.findAll().stream().map(UserResponseDTO::new).toList();
    }
}
