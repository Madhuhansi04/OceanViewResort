package com.oceanview.dao.interfaces;

import com.oceanview.model.Guest;
import java.util.List;

public interface IGuestDAO {
    int addGuest(Guest guest);
    Guest getGuestById(int id);
    List<Guest> getAllGuests();
    boolean updateGuest(Guest guest);
}
