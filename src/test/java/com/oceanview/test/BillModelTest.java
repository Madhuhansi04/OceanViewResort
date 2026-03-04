package com.oceanview.test;

import com.oceanview.model.Bill;
import com.oceanview.model.Reservation;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class BillModelTest {
    private Bill bill;

    @Before
    public void setUp() {
        bill = new Bill();
        bill.setId(1);
        bill.setNights(3);
        bill.setRoomCharge(15000.0);
        bill.setTaxAmount(1500.0);
        bill.setTotalAmount(16500.0);
        bill.setPaymentStatus("PENDING");
    }

    @Test
    public void testBillNotNull() {
        assertNotNull(bill);
    }

    @Test
    public void testBillIdSet() {
        assertEquals(1, bill.getId());
    }

    @Test
    public void testNightsSet() {
        assertEquals(3, bill.getNights());
    }

    @Test
    public void testRoomChargeSet() {
        assertEquals(15000.0, bill.getRoomCharge(), 0.01);
    }

    @Test
    public void testTaxAmountSet() {
        assertEquals(1500.0, bill.getTaxAmount(), 0.01);
    }

    @Test
    public void testTotalAmountSet() {
        assertEquals(16500.0, bill.getTotalAmount(), 0.01);
    }

    @Test
    public void testPaymentStatusPending() {
        assertEquals("PENDING", bill.getPaymentStatus());
    }

    @Test
    public void testPaymentStatusPaid() {
        bill.setPaymentStatus("PAID");
        assertEquals("PAID", bill.getPaymentStatus());
    }

    @Test
    public void testReservationSet() {
        Reservation res = new Reservation();
        res.setReservationNumber("RES-001");
        bill.setReservation(res);
        assertNotNull(bill.getReservation());
    }

    @Test
    public void testTaxIs10Percent() {
        double expectedTax = bill.getRoomCharge() * 0.10;
        assertEquals(expectedTax, bill.getTaxAmount(), 0.01);
    }
}
