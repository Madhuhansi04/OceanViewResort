package com.oceanview.test;

import com.oceanview.model.*;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.Date;

public class ReservationModelTest {
    private Reservation reservation;
    private Guest guest;
    private Room room;

    @Before
    public void setUp() {
        guest = new Guest();
        guest.setName("Saman Silva");
        guest.setContactNumber("0771234567");

        room = new Room();
        room.setRoomNumber("301");

        reservation = new Reservation();
        reservation.setId(1);
        reservation.setReservationNumber("RES-20240301001");
        reservation.setGuest(guest);
        reservation.setRoom(room);
        reservation.setStatus("CONFIRMED");
        reservation.setCheckInDate(new Date());
        reservation.setCheckOutDate(new Date());
    }

    @Test
    public void testReservationNotNull() {
        assertNotNull(reservation);
    }

    @Test
    public void testReservationIdSet() {
        assertEquals(1, reservation.getId());
    }

    @Test
    public void testReservationNumberSet() {
        assertEquals("RES-20240301001",
                reservation.getReservationNumber());
    }

    @Test
    public void testGuestSet() {
        assertNotNull(reservation.getGuest());
    }

    @Test
    public void testRoomSet() {
        assertNotNull(reservation.getRoom());
    }

    @Test
    public void testStatusConfirmed() {
        assertEquals("CONFIRMED", reservation.getStatus());
    }

    @Test
    public void testStatusCheckedIn() {
        reservation.setStatus("CHECKED_IN");
        assertEquals("CHECKED_IN", reservation.getStatus());
    }

    @Test
    public void testStatusCheckedOut() {
        reservation.setStatus("CHECKED_OUT");
        assertEquals("CHECKED_OUT", reservation.getStatus());
    }

    @Test
    public void testStatusCancelled() {
        reservation.setStatus("CANCELLED");
        assertEquals("CANCELLED", reservation.getStatus());
    }

    @Test
    public void testCheckInDateSet() {
        assertNotNull(reservation.getCheckInDate());
    }

    @Test
    public void testCheckOutDateSet() {
        assertNotNull(reservation.getCheckOutDate());
    }

    @Test
    public void testReservationNumberUpdate() {
        reservation.setReservationNumber("RES-20240302001");
        assertEquals("RES-20240302001",
                reservation.getReservationNumber());
    }
}
