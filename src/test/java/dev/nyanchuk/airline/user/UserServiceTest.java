package dev.nyanchuk.airline.user;

import dev.nyanchuk.airline.user.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setRole("USER");

        userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setUsername("testuser");
        userDTO.setRole("USER");
    }

    @Test
    void testCreateUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO createdUser = userService.createUser(userDTO);

        assertEquals(userDTO.getUsername(), createdUser.getUsername());
        assertEquals(userDTO.getRole(), createdUser.getRole());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testGetUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<UserDTO> retrievedUser = userService.getUserById(1L);

        assertTrue(retrievedUser.isPresent());
        assertEquals(userDTO.getUsername(), retrievedUser.get().getUsername());
        assertEquals(userDTO.getRole(), retrievedUser.get().getRole());
    }

    @Test
    void testUpdateUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserDTO updatedUser = userService.updateUser(1L, userDTO);

        assertEquals(userDTO.getUsername(), updatedUser.getUsername());
        assertEquals(userDTO.getRole(), updatedUser.getRole());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetUserById_NotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(1L));
    }
}