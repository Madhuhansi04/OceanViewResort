package com.oceanview.dao.interfaces;

import com.oceanview.model.Reservation;
import java.util.List;


public interface IReservationDAO {
    boolean addReservation(Reservation reservation);
    Reservation getReservationByNumber(String resNumber);
    List<Reservation> getAllReservations();
    boolean updateStatus(int id, String status);
    boolean deleteReservation(int id);
}
