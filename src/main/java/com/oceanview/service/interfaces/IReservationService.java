package com.oceanview.service.interfaces;

import com.oceanview.model.Reservation;
import java.util.List;


public interface IReservationService {
    boolean createReservation(Reservation reservation);
    Reservation getReservation(String reservationNumber);
    List<Reservation> getAllReservations();
    boolean updateStatus(int id, String status);
    boolean cancelReservation(int id);
}
