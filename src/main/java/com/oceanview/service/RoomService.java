package com.oceanview.service;

import com.oceanview.dao.RoomDAO;
import com.oceanview.model.Room;
import com.oceanview.service.interfaces.IRoomService;
import java.util.List;

public class RoomService implements IRoomService {

    private final RoomDAO roomDAO = new RoomDAO();

    @Override
    public List<Room> getAvailableRooms() {
        return roomDAO.getAvailableRooms();
    }

    @Override
    public List<Room> getAllRooms() {
        return roomDAO.getAllRooms();
    }

    @Override
    public Room getRoomById(int id) {
        return roomDAO.getRoomById(id);
    }

    @Override
    public boolean updateRoomStatus(int roomId, String status) {
        return roomDAO.updateRoomStatus(roomId, status);
    }
}

