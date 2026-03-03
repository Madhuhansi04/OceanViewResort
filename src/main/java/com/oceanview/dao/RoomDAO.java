package com.oceanview.dao;

import com.oceanview.dao.interfaces.IRoomDAO;
import com.oceanview.model.Room;
import com.oceanview.model.RoomType;
import com.oceanview.util.DBConnection;
import java.sql.*;
import java.util.*;

public class RoomDAO implements IRoomDAO {

    @Override
    public List<Room> getAvailableRooms() {
        List<Room> list = new ArrayList<>();
        String sql = "SELECT r.*, rt.type_name, rt.price_per_night FROM rooms r " +
                "JOIN room_types rt ON r.room_type_id = rt.id " +
                "WHERE r.status='AVAILABLE'";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setRoomNumber(rs.getString("room_number"));
                room.setStatus(rs.getString("status"));
                room.setFloor(rs.getInt("floor"));
                RoomType rt = new RoomType();
                rt.setTypeName(rs.getString("type_name"));
                rt.setPricePerNight(rs.getDouble("price_per_night"));
                room.setRoomType(rt);
                list.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Room> getAllRooms() {
        List<Room> list = new ArrayList<>();
        String sql = "SELECT r.*, rt.type_name, rt.price_per_night FROM rooms r " +
                "JOIN room_types rt ON r.room_type_id = rt.id";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setRoomNumber(rs.getString("room_number"));
                room.setStatus(rs.getString("status"));
                room.setFloor(rs.getInt("floor"));
                RoomType rt = new RoomType();
                rt.setTypeName(rs.getString("type_name"));
                rt.setPricePerNight(rs.getDouble("price_per_night"));
                room.setRoomType(rt);
                list.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Room getRoomById(int id) {
        String sql = "SELECT r.*, rt.type_name, rt.price_per_night FROM rooms r " +
                "JOIN room_types rt ON r.room_type_id = rt.id WHERE r.id=?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setRoomNumber(rs.getString("room_number"));
                room.setStatus(rs.getString("status"));
                room.setFloor(rs.getInt("floor"));
                RoomType rt = new RoomType();
                rt.setTypeName(rs.getString("type_name"));
                rt.setPricePerNight(rs.getDouble("price_per_night"));
                room.setRoomType(rt);
                return room;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateRoomStatus(int roomId, String status) {
        String sql = "UPDATE rooms SET status=? WHERE id=?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, roomId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}