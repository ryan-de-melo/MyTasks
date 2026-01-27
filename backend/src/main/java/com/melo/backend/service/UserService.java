package com.melo.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.melo.backend.dto.auth.AuthRegisterRequestDTO;
import com.melo.backend.dto.auth.AuthRegisterResponseDTO;
import com.melo.backend.dto.user.UserResponseDTO;
import com.melo.backend.dto.user.UserUpdateDTO;
import com.melo.backend.entity.User;
import com.melo.backend.entity.enums.UserRole;
import com.melo.backend.exception.UserAlreadyExistsException;
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
    public AuthRegisterResponseDTO registerUser(AuthRegisterRequestDTO dto) throws UserAlreadyExistsException {

        if (userRepository.findByEmail(dto.email()) == null) {

            String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
            User toRegister = User.builder()
                                .email(dto.email())
                                .name(dto.name())
                                .password(encryptedPassword)
                                .role(UserRole.USER)   // for simplicity
                                .build();
            userRepository.save(toRegister);
            
            return new AuthRegisterResponseDTO(dto.name(), dto.email());
        } else {
            throw new UserAlreadyExistsException();
        }
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
        return UserMapper.toResponse((User) userRepository.findByEmail(email));
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

    public UserResponseDTO updateById(Long id, UserUpdateDTO dto) {
        Optional<User> opt = userRepository.findById(id);
        User u = null;

        if (opt.isPresent()) {
            User user = opt.get();
            User updatedUser = User.builder()
                    .id(user.getId())
                    .name(dto.name())
                    .password(dto.password())
                    .build();

            u = userRepository.save(updatedUser);
        }
        return UserMapper.toResponse(u);
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
