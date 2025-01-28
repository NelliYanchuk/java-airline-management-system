package dev.nyanchuk.airline.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import dev.nyanchuk.airline.user.exception.UserNotFoundException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Create a new user
    public UserDTO createUser(UserDTO userDTO) {
        User user = toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Encrypt password
        return toDTO(userRepository.save(user));
    }

    // Read all users
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // Read user by ID
    public Optional<UserDTO> getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return Optional.of(toDTO(user));
    }

    // Update an existing user
    public UserDTO updateUser(Long id, UserDTO userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        user.setUsername(userDetails.getUsername());
        user.setRole(userDetails.getRole());
        if (userDetails.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword())); // Encrypt password if updated
        }
        return toDTO(userRepository.save(user));
    }

    // Delete a user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Convert User to UserDTO
    private UserDTO toDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getPassword(), user.getRole()); // Use new constructor
    }

    // Convert UserDTO to User
    private User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setRole(userDTO.getRole());
        user.setPassword(userDTO.getPassword()); // Set password from DTO
        return user;
    }
}