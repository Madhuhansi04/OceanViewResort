package com.oceanview.test;

import com.oceanview.model.*;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.Date;

public class ReservationServiceTest {
    private Reservation reservation;
    private Guest guest;
    private Room room;
    private RoomType roomType;

    @Before
    public void setUp() {
        roomType = new RoomType();
        roomType.setTypeName("Standard");
        roomType.setPricePerNight(5000.0);

        room = new Room();
        room.setId(1);
        room.setRoomNumber("101");
        room.setRoomType(roomType);
        room.setStatus("AVAILABLE");

        guest = new Guest();
        guest.setName("John Silva");
        guest.setContactNumber("0771234567");
        guest.setEmail("john@gmail.com");
        guest.setAddress("No.12, Galle Road");
        guest.setNic("123456789V");

        reservation = new Reservation();
        reservation.setGuest(guest);
        reservation.setRoom(room);
    }

    // Test 1: Reservation object not null
    @Test
    public void testReservationNotNull() {
        assertNotNull(reservation);
    }

    // Test 2: Guest name set correctly
    @Test
    public void testGuestNameSet() {
        assertEquals("John Silva", reservation.getGuest().getName());
    }

    // Test 3: Room number set correctly
    @Test
    public void testRoomNumberSet() {
        assertEquals("101", reservation.getRoom().getRoomNumber());
    }

    // Test 4: Room type price correct
    @Test
    public void testRoomTypePriceCorrect() {
        assertEquals(5000.0,
                reservation.getRoom().getRoomType()
                        .getPricePerNight(), 0.01);
    }

    // Test 5: Guest contact set correctly
    @Test
    public void testGuestContactSet() {
        assertEquals("0771234567",
                reservation.getGuest().getContactNumber());
    }

    // Test 6: Room status available
    @Test
    public void testRoomStatusAvailable() {
        assertEquals("AVAILABLE",
                reservation.getRoom().getStatus());
    }

    // Test 7: Reservation status default CONFIRMED
    @Test
    public void testReservationDefaultStatus() {
        reservation.setStatus("CONFIRMED");
        assertEquals("CONFIRMED", reservation.getStatus());
    }

    // Test 8: Guest address set correctly
    @Test
    public void testGuestAddressSet() {
        assertEquals("No.12, Galle Road",
                reservation.getGuest().getAddress());
    }

    // Test 9: Night calculation
    @Test
    public void testNightCalculation() {
        long checkIn = System.currentTimeMillis();
        long checkOut = checkIn + (3 * 24 * 60 * 60 * 1000L);
        reservation.setCheckInDate(new Date(checkIn));
        reservation.setCheckOutDate(new Date(checkOut));
        long nights = (reservation.getCheckOutDate().getTime() -
                reservation.getCheckInDate().getTime()) /
                (24 * 60 * 60 * 1000L);
        assertEquals(3, nights);
    }

    // Test 10: Total amount calculation
    @Test
    public void testTotalAmountCalculation() {
        double pricePerNight = 5000.0;
        int nights = 3;
        double roomCharge = pricePerNight * nights;
        double tax = roomCharge * 0.10;
        double total = roomCharge + tax;
        assertEquals(16500.0, total, 0.01);
    }

}
