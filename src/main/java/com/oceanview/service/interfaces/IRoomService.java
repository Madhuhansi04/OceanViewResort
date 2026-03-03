package com.oceanview.service.interfaces;

import com.oceanview.model.Room;
import java.util.List;

public interface IRoomService {
    List<Room> getAvailableRooms();
    List<Room> getAllRooms();
    Room getRoomById(int id);
    boolean updateRoomStatus(int roomId, String status);
}
