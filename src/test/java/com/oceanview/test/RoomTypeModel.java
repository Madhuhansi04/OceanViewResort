package com.oceanview.test;

import com.oceanview.model.RoomType;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class RoomTypeModel {
    private RoomType standard;
    private RoomType deluxe;
    private RoomType suite;

    @Before
    public void setUp() {
        standard = new RoomType();
        standard.setId(1);
        standard.setTypeName("Standard");
        standard.setPricePerNight(5000.0);
        standard.setDescription("Standard room with basic amenities");

        deluxe = new RoomType();
        deluxe.setId(2);
        deluxe.setTypeName("Deluxe");
        deluxe.setPricePerNight(8000.0);
        deluxe.setDescription("Deluxe room with sea view");

        suite = new RoomType();
        suite.setId(3);
        suite.setTypeName("Suite");
        suite.setPricePerNight(15000.0);
        suite.setDescription("Luxury suite with all amenities");
    }

    @Test
    public void testStandardRoomNotNull() {
        assertNotNull(standard);
    }

    @Test
    public void testStandardRoomId() {
        assertEquals(1, standard.getId());
    }

    @Test
    public void testStandardRoomName() {
        assertEquals("Standard", standard.getTypeName());
    }

    @Test
    public void testStandardRoomPrice() {
        assertEquals(5000.0, standard.getPricePerNight(), 0.01);
    }

    @Test
    public void testDeluxeRoomName() {
        assertEquals("Deluxe", deluxe.getTypeName());
    }

    @Test
    public void testDeluxeRoomPrice() {
        assertEquals(8000.0, deluxe.getPricePerNight(), 0.01);
    }

    @Test
    public void testSuiteRoomName() {
        assertEquals("Suite", suite.getTypeName());
    }

    @Test
    public void testSuiteRoomPrice() {
        assertEquals(15000.0, suite.getPricePerNight(), 0.01);
    }

    @Test
    public void testStandardCheaperThanDeluxe() {
        assertTrue(standard.getPricePerNight()
                < deluxe.getPricePerNight());
    }

    @Test
    public void testDeluxeCheaperThanSuite() {
        assertTrue(deluxe.getPricePerNight()
                < suite.getPricePerNight());
    }

    @Test
    public void testStandardDescription() {
        assertEquals("Standard room with basic amenities",
                standard.getDescription());
    }

    @Test
    public void testPriceUpdate() {
        standard.setPricePerNight(6000.0);
        assertEquals(6000.0,
                standard.getPricePerNight(), 0.01);
    }
}
