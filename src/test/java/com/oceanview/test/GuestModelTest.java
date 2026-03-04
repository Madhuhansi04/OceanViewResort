package com.oceanview.test;

import com.oceanview.model.Guest;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class GuestModelTest {
    private Guest guest;

    @Before
    public void setUp() {
        guest = new Guest();
        guest.setId(1);
        guest.setName("Kamal Perera");
        guest.setContactNumber("0771234567");
        guest.setEmail("kamal@gmail.com");
        guest.setAddress("No.45, Colombo Road, Galle");
        guest.setNic("987654321V");
    }

    // Test 1: Guest not null
    @Test
    public void testGuestNotNull() {
        assertNotNull(guest);
    }

    // Test 2: Guest ID set
    @Test
    public void testGuestIdSet() {
        assertEquals(1, guest.getId());
    }

    // Test 3: Guest name set
    @Test
    public void testGuestNameSet() {
        assertEquals("Kamal Perera", guest.getName());
    }

    // Test 4: Guest contact set
    @Test
    public void testGuestContactSet() {
        assertEquals("0771234567", guest.getContactNumber());
    }

    // Test 5: Guest email set
    @Test
    public void testGuestEmailSet() {
        assertEquals("kamal@gmail.com", guest.getEmail());
    }

    // Test 6: Guest address set
    @Test
    public void testGuestAddressSet() {
        assertEquals("No.45, Colombo Road, Galle",
                guest.getAddress());
    }

    // Test 7: Guest NIC set
    @Test
    public void testGuestNicSet() {
        assertEquals("987654321V", guest.getNic());
    }

    // Test 8: Guest name update
    @Test
    public void testGuestNameUpdate() {
        guest.setName("Nimal Silva");
        assertEquals("Nimal Silva", guest.getName());
    }

    // Test 9: Guest email update
    @Test
    public void testGuestEmailUpdate() {
        guest.setEmail("nimal@yahoo.com");
        assertEquals("nimal@yahoo.com", guest.getEmail());
    }

    // Test 10: Guest contact update
    @Test
    public void testGuestContactUpdate() {
        guest.setContactNumber("0712345678");
        assertEquals("0712345678", guest.getContactNumber());
    }
}
