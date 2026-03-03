package com.oceanview.service;

import com.oceanview.dao.ReservationDAO;
import com.oceanview.model.Bill;
import com.oceanview.model.Reservation;
import com.oceanview.service.interfaces.IBillingService;
import com.oceanview.util.DBConnection;
import java.sql.*;

public class BillingService implements IBillingService {

    private final ReservationDAO reservationDAO = new ReservationDAO();

    @Override
    public Bill generateBill(int reservationId) {
        String sql = "SELECT * FROM reservations WHERE id=?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, reservationId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Reservation res = reservationDAO
                        .getReservationByNumber(rs.getString("reservation_number"));
                if (res == null) return null;

                long diff = res.getCheckOutDate().getTime()
                        - res.getCheckInDate().getTime();
                int nights = (int)(diff / (1000 * 60 * 60 * 24));
                if (nights < 1) nights = 1;

                double pricePerNight = res.getRoom()
                        .getRoomType().getPricePerNight();
                double roomCharge = nights * pricePerNight;
                double tax = roomCharge * 0.10;
                double total = roomCharge + tax;

                Bill bill = new Bill();
                bill.setReservation(res);
                bill.setNights(nights);
                bill.setRoomCharge(roomCharge);
                bill.setTaxAmount(tax);
                bill.setTotalAmount(total);
                bill.setPaymentStatus("PENDING");

                saveBill(bill, conn);
                return bill;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void saveBill(Bill bill, Connection conn) throws SQLException {
        String sql = "INSERT INTO bills (reservation_id, nights, room_charge, " +
                "tax_amount, total_amount, payment_status) VALUES (?,?,?,?,?,?) " +
                "ON DUPLICATE KEY UPDATE total_amount=VALUES(total_amount)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bill.getReservation().getId());
            ps.setInt(2, bill.getNights());
            ps.setDouble(3, bill.getRoomCharge());
            ps.setDouble(4, bill.getTaxAmount());
            ps.setDouble(5, bill.getTotalAmount());
            ps.setString(6, bill.getPaymentStatus());
            ps.executeUpdate();
        }
    }

    @Override
    public Bill getBillByReservationId(int reservationId) {
        String sql = "SELECT b.*, r.reservation_number FROM bills b " +
                "JOIN reservations r ON b.reservation_id = r.id " +
                "WHERE b.reservation_id=?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, reservationId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setNights(rs.getInt("nights"));
                bill.setRoomCharge(rs.getDouble("room_charge"));
                bill.setTaxAmount(rs.getDouble("tax_amount"));
                bill.setTotalAmount(rs.getDouble("total_amount"));
                bill.setPaymentStatus(rs.getString("payment_status"));
                return bill;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updatePaymentStatus(int billId, String status) {
        String sql = "UPDATE bills SET payment_status=? WHERE id=?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, billId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

