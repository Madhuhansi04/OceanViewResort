package com.oceanview.servlet;

import com.oceanview.model.Bill;
import com.oceanview.service.BillingService;
import com.oceanview.util.SessionUtil;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/BillServlet")
public class BillServlet extends HttpServlet {

    private final BillingService billingService =
            new BillingService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        if (!SessionUtil.isLoggedIn(request)) {
            response.sendRedirect(
                    request.getContextPath() + "/login.jsp");
            return;
        }

        String reservationIdStr =
                request.getParameter("reservationId");

        if (reservationIdStr != null) {
            int reservationId =
                    Integer.parseInt(reservationIdStr);
            Bill bill = billingService
                    .generateBill(reservationId);
            request.setAttribute("bill", bill);
            request.getRequestDispatcher("/billing/bill.jsp")
                    .forward(request, response);
        } else {
            response.sendRedirect(
                    request.getContextPath() +
                            "/ReservationServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String billIdStr = request.getParameter("billId");
        String status    = request.getParameter("status");

        if (billIdStr != null && status != null) {
            int billId = Integer.parseInt(billIdStr);
            billingService.updatePaymentStatus(billId, status);
        }
        response.sendRedirect(
                request.getContextPath() + "/ReservationServlet");
    }
}

