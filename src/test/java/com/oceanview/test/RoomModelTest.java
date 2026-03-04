package com.oceanview.test;

import com.oceanview.model.Room;
import com.oceanview.model.RoomType;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class RoomModelTest {
    private Room room;
    private RoomType roomType;

    @Before
    public void setUp() {
        roomType = new RoomType();
        roomType.setId(1);
        roomType.setTypeName("Standard");
        roomType.setPricePerNight(5000.0);

        room = new Room();
        room.setId(1);
        room.setRoomNumber("101");
        room.setFloor(1);
        room.setStatus("AVAILABLE");
        room.setRoomType(roomType);
    }

    // Test 1: Room not null
    @Test
    public void testRoomNotNull() {
        assertNotNull(room);
    }

    // Test 2: Room ID set
    @Test
    public void testRoomIdSet() {
        assertEquals(1, room.getId());
    }

    // Test 3: Room number set
    @Test
    public void testRoomNumberSet() {
        assertEquals("101", room.getRoomNumber());
    }

    // Test 4: Room floor set
    @Test
    public void testRoomFloorSet() {
        assertEquals(1, room.getFloor());
    }

    // Test 5: Room status AVAILABLE
    @Test
    public void testRoomStatusAvailable() {
        assertEquals("AVAILABLE", room.getStatus());
    }

    // Test 6: Room status update to OCCUPIED
    @Test
    public void testRoomStatusOccupied() {
        room.setStatus("OCCUPIED");
        assertEquals("OCCUPIED", room.getStatus());
    }

    // Test 7: Room type name set
    @Test
    public void testRoomTypeNameSet() {
        assertEquals("Standard",
                room.getRoomType().getTypeName());
    }

    // Test 8: Room type price set
    @Test
    public void testRoomTypePriceSet() {
        assertEquals(5000.0,
                room.getRoomType().getPricePerNight(), 0.01);
    }

    // Test 9: Room type Deluxe price
    @Test
    public void testDeluxeRoomPrice() {
        roomType.setTypeName("Deluxe");
        roomType.setPricePerNight(8000.0);
        assertEquals(8000.0,
                room.getRoomType().getPricePerNight(), 0.01);
    }

    // Test 10: Room type Suite price
    @Test
    public void testSuiteRoomPrice() {
        roomType.setTypeName("Suite");
        roomType.setPricePerNight(15000.0);
        assertEquals(15000.0,
                room.getRoomType().getPricePerNight(), 0.01);
    }
}
