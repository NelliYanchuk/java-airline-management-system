package dev.nyanchuk.airline.user;

import dev.nyanchuk.airline.role.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserDTOTest {

    @Test
    public void testDefaultConstructor() {
        UserDTO user = new UserDTO();
        assertNull(user.getId());
        assertNull(user.getUsername());
        assertNull(user.getPassword());
        assertNull(user.getRole());
    }

    @Test
    public void testConstructorWithIdUsernameRole() {
        UserDTO user = new UserDTO(1L, "username", Role.USER);
        assertEquals(1L, user.getId());
        assertEquals("username", user.getUsername());
        assertNull(user.getPassword());
        assertEquals(Role.USER, user.getRole());
    }

    @Test
    public void testConstructorWithAllFields() {
        UserDTO user = new UserDTO(1L, "username", "password", Role.ADMIN);
        assertEquals(1L, user.getId());
        assertEquals("username", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals(Role.ADMIN, user.getRole());
    }

    @Test
    public void testSettersAndGetters() {
        UserDTO user = new UserDTO();
        user.setId(2L);
        user.setUsername("newUsername");
        user.setPassword("newPassword");
        user.setRole(Role.USER);

        assertEquals(2L, user.getId());
        assertEquals("newUsername", user.getUsername());
        assertEquals("newPassword", user.getPassword());
        assertEquals(Role.USER, user.getRole());
    }
}