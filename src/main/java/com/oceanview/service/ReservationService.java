package com.oceanview.service;

import com.oceanview.dao.ReservationDAO;
import com.oceanview.dao.RoomDAO;
import com.oceanview.model.Reservation;
import com.oceanview.service.interfaces.IReservationService;
import java.util.List;
import java.util.UUID;

public class ReservationService implements IReservationService {

    private final ReservationDAO reservationDAO = new ReservationDAO();
    private final RoomDAO roomDAO = new RoomDAO();

    @Override
    public boolean createReservation(Reservation reservation) {
        String resNumber = "RES-" + UUID.randomUUID().toString()
                .substring(0, 8).toUpperCase();
        reservation.setReservationNumber(resNumber);
        reservation.setStatus("CONFIRMED");
        boolean result = reservationDAO.addReservation(reservation);
        if (result) {
            roomDAO.updateRoomStatus(
                    reservation.getRoom().getId(), "OCCUPIED");
        }
        return result;
    }

    @Override
    public Reservation getReservation(String reservationNumber) {
        return reservationDAO.getReservationByNumber(reservationNumber);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationDAO.getAllReservations();
    }

    @Override
    public boolean updateStatus(int id, String status) {
        return reservationDAO.updateStatus(id, status);
    }

    @Override
    public boolean cancelReservation(int id) {
        return reservationDAO.updateStatus(id, "CANCELLED");
    }
}
