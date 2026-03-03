package com.oceanview.servlet;

import com.oceanview.dao.GuestDAO;
import com.oceanview.model.*;
import com.oceanview.service.*;
import com.oceanview.util.SessionUtil;
import com.oceanview.util.ValidationUtil;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/ReservationServlet")
public class ReservationServlet extends HttpServlet {

    private final ReservationService reservationService =
            new ReservationService();
    private final RoomService roomService = new RoomService();
    private final GuestDAO guestDAO = new GuestDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        if (!SessionUtil.isLoggedIn(request)) {
            response.sendRedirect(
                    request.getContextPath() + "/login.jsp");
            return;
        }

        String action = request.getParameter("action");
        if ("add".equals(action)) {
            request.setAttribute("availableRooms",
                    roomService.getAvailableRooms());
            request.getRequestDispatcher("/reservations/add.jsp")
                    .forward(request, response);
        } else if ("cancel".equals(action)) {
            String idStr = request.getParameter("id");
            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                reservationService.cancelReservation(id);
                request.getSession().setAttribute("success",
                        "Reservation cancelled successfully!");
            }
            response.sendRedirect(
                    request.getContextPath() + "/ReservationServlet");
            return;
        } else if ("checkin".equals(action)) {
            String idStr = request.getParameter("id");
            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                reservationService.updateStatus(id, "CHECKED_IN");
                request.getSession().setAttribute("success",
                        "Guest checked in successfully!");
            }
            response.sendRedirect(
                    request.getContextPath() + "/ReservationServlet");
            return;
        } else if ("checkout".equals(action)) {
            String idStr = request.getParameter("id");
            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                reservationService.updateStatus(id, "CHECKED_OUT");
                request.getSession().setAttribute("success",
                        "Guest checked out successfully!");
            }
            response.sendRedirect(
                    request.getContextPath() + "/ReservationServlet");
            return;
        }else {
            String searchNum = request.getParameter("search");
            if (searchNum != null && !searchNum.isEmpty()) {
                com.oceanview.model.Reservation res =
                        reservationService.getReservation(searchNum);
                if (res != null) {
                    request.setAttribute("searchResult", res);
                } else {
                    request.setAttribute("searchError",
                            "Reservation not found: " + searchNum);
                }
            }
            request.setAttribute("reservations",
                    reservationService.getAllReservations());
            request.getRequestDispatcher("/reservations/list.jsp")
                    .forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String guestName = request.getParameter("guestName");
        String contact   = request.getParameter("contact");
        String email     = request.getParameter("email");
        String address = request.getParameter("address");
        String nic       = request.getParameter("nic");
        String roomIdStr = request.getParameter("roomId");
        String checkIn   = request.getParameter("checkIn");
        String checkOut  = request.getParameter("checkOut");

        // Validation
        String error = null;

        if (ValidationUtil.isNullOrEmpty(guestName)) {
            error = "Guest name is required!";
        } else if (guestName.length() < 3) {
            error = "Guest name must be at least 3 characters!";
        } else if (ValidationUtil.isNullOrEmpty(contact)) {
            error = "Contact number is required!";
        } else if (!ValidationUtil.isValidContact(contact)) {
            error = "Contact number must be 10 digits!";
        } else if (!ValidationUtil.isNullOrEmpty(email) &&
                !ValidationUtil.isValidEmail(email)) {
            error = "Invalid email format!";
        } else if (ValidationUtil.isNullOrEmpty(roomIdStr)) {
            error = "Please select a room!";
        } else if (ValidationUtil.isNullOrEmpty(checkIn)) {
            error = "Check-in date is required!";
        } else if (ValidationUtil.isNullOrEmpty(checkOut)) {
            error = "Check-out date is required!";
        } else {
            // Date validation
            try {
                SimpleDateFormat sdf =
                        new SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false);
                Date checkInDate  = sdf.parse(checkIn);
                Date checkOutDate = sdf.parse(checkOut);
                Date today = new Date();

                if (checkInDate.before(today) &&
                        !isSameDay(checkInDate, today)) {
                    error = "Check-in date cannot be in the past!";
                } else if (!checkOutDate.after(checkInDate)) {
                    error = "Check-out date must be after " +
                            "check-in date!";
                }
            } catch (Exception e) {
                error = "Invalid date format!";
            }
        }

        if (error != null) {
            request.setAttribute("error", error);
            request.setAttribute("availableRooms",
                    roomService.getAvailableRooms());
            request.getRequestDispatcher("/reservations/add.jsp")
                    .forward(request, response);
            return;
        }

        try {
            Guest guest = new Guest();
            guest.setName(guestName.trim());
            guest.setContactNumber(contact.trim());
            guest.setAddress(address);
            guest.setEmail(email);
            guest.setNic(nic);
            int guestId = guestDAO.addGuest(guest);
            guest.setId(guestId);

            Room room = new Room();
            room.setId(Integer.parseInt(roomIdStr));

            SimpleDateFormat sdf =
                    new SimpleDateFormat("yyyy-MM-dd");
            Reservation reservation = new Reservation();
            reservation.setGuest(guest);
            reservation.setRoom(room);
            reservation.setCheckInDate(sdf.parse(checkIn));
            reservation.setCheckOutDate(sdf.parse(checkOut));

            boolean success =
                    reservationService.createReservation(reservation);

            if (success) {
                String emailMsg = "";
                if (email != null && !email.isEmpty()) {
                    emailMsg = " 📧 Confirmation email sent to " + email;
                }
                request.getSession().setAttribute("success",
                        "✅ Reservation " +
                                reservation.getReservationNumber() +
                                " created successfully!" + emailMsg);
                response.sendRedirect(
                        request.getContextPath() +
                                "/ReservationServlet");
            } else {
                request.setAttribute("error",
                        "Failed to create reservation!");
                request.setAttribute("availableRooms",
                        roomService.getAvailableRooms());
                request.getRequestDispatcher(
                                "/reservations/add.jsp")
                        .forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error",
                    "Error: " + e.getMessage());
            request.setAttribute("availableRooms",
                    roomService.getAvailableRooms());
            request.getRequestDispatcher("/reservations/add.jsp")
                    .forward(request, response);
        }
    }

    private boolean isSameDay(Date d1, Date d2) {
        SimpleDateFormat fmt =
                new SimpleDateFormat("yyyyMMdd");
        return fmt.format(d1).equals(fmt.format(d2));
    }
}


