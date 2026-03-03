package com.oceanview.service.interfaces;

import com.oceanview.model.Bill;

public interface IBillingService {
    Bill generateBill(int reservationId);
    Bill getBillByReservationId(int reservationId);
    boolean updatePaymentStatus(int billId, String status);
}
