package dev.nyanchuk.airline.user;

import dev.nyanchuk.airline.role.Role;

public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private Role role;

    // Constructors
    public UserDTO() {}

    public UserDTO(Long id, String username, Role role) {  // Existing constructor
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public UserDTO(Long id, String username, String password, Role role) {  // New constructor
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}