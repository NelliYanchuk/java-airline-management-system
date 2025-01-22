package dev.nyanchuk.airline.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import dev.nyanchuk.airline.user.exception.UserNotFoundException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // Create a new user
    public UserDTO createUser(UserDTO userDTO) {
        User user = toEntity(userDTO);
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
        return toDTO(userRepository.save(user));
    }

    // Delete a user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Convert User to UserDTO
    private UserDTO toDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getRole());
    }

    // Convert UserDTO to User
    private User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setRole(userDTO.getRole());
        return user;
    }
}