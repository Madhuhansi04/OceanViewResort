package com.oceanview.dao;

import com.oceanview.dao.interfaces.IReservationDAO;
import com.oceanview.model.*;
import com.oceanview.util.DBConnection;
import java.sql.*;
import java.util.*;

public class ReservationDAO implements IReservationDAO {

    @Override
    public boolean addReservation(Reservation reservation) {
        String sql = "INSERT INTO reservations (reservation_number, guest_id, room_id, " +
                "check_in_date, check_out_date, status, total_amount) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, reservation.getReservationNumber());
            ps.setInt(2, reservation.getGuest().getId());
            ps.setInt(3, reservation.getRoom().getId());
            ps.setDate(4, new java.sql.Date(reservation.getCheckInDate().getTime()));
            ps.setDate(5, new java.sql.Date(reservation.getCheckOutDate().getTime()));
            ps.setString(6, reservation.getStatus());
            ps.setDouble(7, reservation.getTotalAmount());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Reservation getReservationByNumber(String resNumber) {
        String sql = "SELECT r.*, g.name as guest_name, g.contact_number, " +
                "rm.room_number, rt.price_per_night, rt.type_name " +
                "FROM reservations r " +
                "JOIN guests g ON r.guest_id = g.id " +
                "JOIN rooms rm ON r.room_id = rm.id " +
                "JOIN room_types rt ON rm.room_type_id = rt.id " +
                "WHERE r.reservation_number=?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, resNumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapReservation(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT r.*, g.name as guest_name, g.contact_number, " +
                "rm.room_number, rt.price_per_night, rt.type_name " +
                "FROM reservations r " +
                "JOIN guests g ON r.guest_id = g.id " +
                "JOIN rooms rm ON r.room_id = rm.id " +
                "JOIN room_types rt ON rm.room_type_id = rt.id " +
                "ORDER BY r.created_at DESC";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapReservation(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean updateStatus(int id, String status) {
        String sql = "UPDATE reservations SET status=? WHERE id=?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteReservation(int id) {
        String sql = "DELETE FROM reservations WHERE id=?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Reservation mapReservation(ResultSet rs) throws SQLException {
        Reservation res = new Reservation();
        res.setId(rs.getInt("id"));
        res.setReservationNumber(rs.getString("reservation_number"));
        res.setStatus(rs.getString("status"));
        res.setTotalAmount(rs.getDouble("total_amount"));
        res.setCheckInDate(rs.getDate("check_in_date"));
        res.setCheckOutDate(rs.getDate("check_out_date"));

        Guest g = new Guest();
        g.setId(rs.getInt("guest_id"));
        g.setName(rs.getString("guest_name"));
        g.setContactNumber(rs.getString("contact_number"));
        res.setGuest(g);

        Room room = new Room();
        room.setId(rs.getInt("room_id"));
        room.setRoomNumber(rs.getString("room_number"));
        RoomType rt = new RoomType();
        rt.setPricePerNight(rs.getDouble("price_per_night"));
        rt.setTypeName(rs.getString("type_name"));
        room.setRoomType(rt);
        res.setRoom(room);

        return res;
    }
}
