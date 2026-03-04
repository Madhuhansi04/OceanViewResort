package com.oceanview.test;

import com.oceanview.model.User;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class UserModelTest {
    private User user;

    @Before
    public void setUp() {
        user = new User();
        user.setId(1);
        user.setUsername("admin");
        user.setPassword("admin123");
        user.setFullName("Administrator");
        user.setRole("ADMIN");
    }

    @Test
    public void testUserNotNull() {
        assertNotNull(user);
    }

    @Test
    public void testUserIdSet() {
        assertEquals(1, user.getId());
    }

    @Test
    public void testUsernameSet() {
        assertEquals("admin", user.getUsername());
    }

    @Test
    public void testPasswordSet() {
        assertEquals("admin123", user.getPassword());
    }

    @Test
    public void testFullNameSet() {
        assertEquals("Administrator", user.getFullName());
    }


    @Test
    public void testRoleSet() {
        assertEquals("ADMIN", user.getRole());
    }

    @Test
    public void testUsernameUpdate() {
        user.setUsername("manager");
        assertEquals("manager", user.getUsername());
    }

    @Test
    public void testPasswordUpdate() {
        user.setPassword("newpass123");
        assertEquals("newpass123", user.getPassword());
    }

    @Test
    public void testRoleUpdate() {
        user.setRole("MANAGER");
        assertEquals("MANAGER", user.getRole());
    }
}
