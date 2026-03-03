package com.oceanview.dao;

import com.oceanview.dao.interfaces.IGuestDAO;
import com.oceanview.model.Guest;
import com.oceanview.util.DBConnection;
import java.sql.*;
import java.util.*;

public class GuestDAO implements IGuestDAO {

    @Override
    public int addGuest(Guest guest) {
        String sql = "INSERT INTO guests (name, address, contact_number, email, nic) VALUES (?,?,?,?,?)";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, guest.getName());
            ps.setString(2, guest.getAddress());
            ps.setString(3, guest.getContactNumber());
            ps.setString(4, guest.getEmail());
            ps.setString(5, guest.getNic());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Guest getGuestById(int id) {
        String sql = "SELECT * FROM guests WHERE id=?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Guest g = new Guest();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setAddress(rs.getString("address"));
                g.setContactNumber(rs.getString("contact_number"));
                g.setEmail(rs.getString("email"));
                g.setNic(rs.getString("nic"));
                return g;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Guest> getAllGuests() {
        List<Guest> list = new ArrayList<>();
        String sql = "SELECT * FROM guests";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Guest g = new Guest();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setContactNumber(rs.getString("contact_number"));
                g.setEmail(rs.getString("email"));
                g.setNic(rs.getString("nic"));
                list.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean updateGuest(Guest guest) {
        String sql = "UPDATE guests SET name=?, address=?, contact_number=?, email=?, nic=? WHERE id=?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, guest.getName());
            ps.setString(2, guest.getAddress());
            ps.setString(3, guest.getContactNumber());
            ps.setString(4, guest.getEmail());
            ps.setString(5, guest.getNic());
            ps.setInt(6, guest.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
