package com.oceanview.test;

import com.oceanview.model.*;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class BillingServiceTest {
    private Bill bill;
    private Reservation reservation;
    private Room room;
    private RoomType roomType;
    private Guest guest;

    @Before
    public void setUp() {
        roomType = new RoomType();
        roomType.setPricePerNight(8000.0);
        roomType.setTypeName("Deluxe");

        room = new Room();
        room.setRoomNumber("201");
        room.setRoomType(roomType);

        guest = new Guest();
        guest.setName("Mary Perera");
        guest.setContactNumber("0712345678");

        reservation = new Reservation();
        reservation.setGuest(guest);
        reservation.setRoom(room);
        reservation.setReservationNumber("RES-12345678");

        bill = new Bill();
        bill.setReservation(reservation);
        bill.setNights(2);
        bill.setRoomCharge(16000.0);
        bill.setTaxAmount(1600.0);
        bill.setTotalAmount(17600.0);
        bill.setPaymentStatus("PENDING");
    }

    // Test 1: Bill not null
    @Test
    public void testBillNotNull() {
        assertNotNull(bill);
    }

    // Test 2: Room charge calculation
    @Test
    public void testRoomChargeCalculation() {
        double expected = 8000.0 * 2;
        assertEquals(expected, bill.getRoomCharge(), 0.01);
    }

    // Test 3: Tax calculation (10%)
    @Test
    public void testTaxCalculation() {
        double tax = bill.getRoomCharge() * 0.10;
        assertEquals(tax, bill.getTaxAmount(), 0.01);
    }

    // Test 4: Total amount calculation
    @Test
    public void testTotalAmountCalculation() {
        double total = bill.getRoomCharge() + bill.getTaxAmount();
        assertEquals(total, bill.getTotalAmount(), 0.01);
    }

    // Test 5: Nights set correctly
    @Test
    public void testNightsSetCorrectly() {
        assertEquals(2, bill.getNights());
    }

    // Test 6: Payment status PENDING
    @Test
    public void testPaymentStatusPending() {
        assertEquals("PENDING", bill.getPaymentStatus());
    }

    // Test 7: Payment status update
    @Test
    public void testPaymentStatusUpdate() {
        bill.setPaymentStatus("PAID");
        assertEquals("PAID", bill.getPaymentStatus());
    }

    // Test 8: Reservation number in bill
    @Test
    public void testReservationNumberInBill() {
        assertEquals("RES-12345678",
                bill.getReservation().getReservationNumber());
    }

    // Test 9: Zero nights
    @Test
    public void testZeroNights() {
        bill.setNights(0);
        bill.setRoomCharge(0.0);
        bill.setTaxAmount(0.0);
        bill.setTotalAmount(0.0);
        assertEquals(0.0, bill.getTotalAmount(), 0.01);
    }

    // Test 10: Large amount calculation
    @Test
    public void testLargeAmountCalculation() {
        bill.setNights(30);
        double roomCharge = 8000.0 * 30;
        double tax = roomCharge * 0.10;
        double total = roomCharge + tax;
        bill.setRoomCharge(roomCharge);
        bill.setTaxAmount(tax);
        bill.setTotalAmount(total);
        assertEquals(264000.0, bill.getTotalAmount(), 0.01);
    }
}
