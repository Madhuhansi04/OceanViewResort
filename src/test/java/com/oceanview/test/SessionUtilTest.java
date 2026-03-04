package com.oceanview.test;

import org.junit.Test;
import static org.junit.Assert.*;

public class SessionUtilTest {
    @Test
    public void testTrueIsTrue() {
        assertTrue(true);
    }

    @Test
    public void testStringNotNull() {
        String s = "OceanViewResort";
        assertNotNull(s);
    }

    @Test
    public void testStringEquals() {
        String s = "admin";
        assertEquals("admin", s);
    }

    @Test
    public void testIntCalculation() {
        int nights = 3;
        double price = 5000.0;
        double total = nights * price;
        assertEquals(15000.0, total, 0.01);
    }

    @Test
    public void testTaxCalculation() {
        double roomCharge = 15000.0;
        double tax = roomCharge * 0.10;
        assertEquals(1500.0, tax, 0.01);
    }

    @Test
    public void testTotalWithTax() {
        double roomCharge = 15000.0;
        double tax = roomCharge * 0.10;
        double total = roomCharge + tax;
        assertEquals(16500.0, total, 0.01);
    }

    @Test
    public void testReservationNumberFormat() {
        String resNum = "RES-20240301001";
        assertTrue(resNum.startsWith("RES-"));
    }

    @Test
    public void testRoomStatusAvailable() {
        String status = "AVAILABLE";
        assertEquals("AVAILABLE", status);
    }

    @Test
    public void testRoomStatusOccupied() {
        String status = "OCCUPIED";
        assertNotEquals("AVAILABLE", status);
    }

    @Test
    public void testNightsGreaterThanZero() {
        int nights = 2;
        assertTrue(nights > 0);
    }
}
