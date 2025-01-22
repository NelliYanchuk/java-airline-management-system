package dev.nyanchuk.airline.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import dev.nyanchuk.airline.user.exception.UserNotFoundException;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    // Create a new user
    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    // Read all users
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    // Read user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.getUserById(id).orElseThrow(() -> new UserNotFoundException(id));
        return ResponseEntity.ok().body(userDTO);
    }

    // Update an existing user
    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDetails) {
        return userService.updateUser(id, userDetails);
    }

    // Delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}