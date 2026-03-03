package com.oceanview.servlet;

import com.oceanview.service.ReservationService;
import com.oceanview.service.RoomService;
import com.oceanview.util.SessionUtil;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {

    private final ReservationService reservationService =
            new ReservationService();
    private final RoomService roomService = new RoomService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        if (!SessionUtil.isLoggedIn(request)) {
            response.sendRedirect(
                    request.getContextPath() + "/login.jsp");
            return;
        }

        request.setAttribute("reservations",
                reservationService.getAllReservations());
        request.setAttribute("availableRooms",
                roomService.getAvailableRooms());
        request.setAttribute("allRooms",
                roomService.getAllRooms());

        request.getRequestDispatcher("/dashboard.jsp")
                .forward(request, response);
    }
}
