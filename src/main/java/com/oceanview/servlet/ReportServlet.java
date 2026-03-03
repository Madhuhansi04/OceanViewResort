package com.oceanview.servlet;

import com.oceanview.util.DBConnection;
import com.oceanview.util.SessionUtil;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        if (!SessionUtil.isLoggedIn(request)) {
            response.sendRedirect(
                    request.getContextPath() + "/login.jsp");
            return;
        }

        String type = request.getParameter("type");
        if (type == null) type = "revenue";

        try (Connection conn =
                     DBConnection.getInstance().getConnection()) {

            if ("revenue".equals(type)) {
                // Monthly Revenue Report
                String sql =
                        "SELECT MONTH(created_at) as month, " +
                                "YEAR(created_at) as year, " +
                                "COUNT(*) as total_reservations, " +
                                "SUM(total_amount) as total_revenue " +
                                "FROM reservations " +
                                "WHERE status != 'CANCELLED' " +
                                "GROUP BY YEAR(created_at), MONTH(created_at) " +
                                "ORDER BY year DESC, month DESC";

                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                List<Map<String, Object>> data = new ArrayList<>();
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    row.put("month", rs.getInt("month"));
                    row.put("year", rs.getInt("year"));
                    row.put("total_reservations",
                            rs.getInt("total_reservations"));
                    row.put("total_revenue",
                            rs.getDouble("total_revenue"));
                    data.add(row);
                }
                request.setAttribute("revenueData", data);

            } else if ("rooms".equals(type)) {
                // Room Availability Report
                String sql =
                        "SELECT rm.room_number, rt.type_name, " +
                                "rt.price_per_night, rm.status, rm.floor " +
                                "FROM rooms rm " +
                                "JOIN room_types rt " +
                                "ON rm.room_type_id = rt.id " +
                                "ORDER BY rm.status, rm.room_number";

                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                List<Map<String, Object>> data = new ArrayList<>();
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    row.put("room_number",
                            rs.getString("room_number"));
                    row.put("type_name",
                            rs.getString("type_name"));
                    row.put("price_per_night",
                            rs.getDouble("price_per_night"));
                    row.put("status", rs.getString("status"));
                    row.put("floor", rs.getInt("floor"));
                    data.add(row);
                }
                request.setAttribute("roomData", data);

            } else if ("guests".equals(type)) {
                // Guest History Report
                String sql =
                        "SELECT g.name, g.contact_number, g.nic, " +
                                "COUNT(r.id) as total_visits, " +
                                "SUM(r.total_amount) as total_spent " +
                                "FROM guests g " +
                                "LEFT JOIN reservations r ON g.id = r.guest_id " +
                                "GROUP BY g.id " +
                                "ORDER BY total_visits DESC";

                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                List<Map<String, Object>> data = new ArrayList<>();
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    row.put("name", rs.getString("name"));
                    row.put("contact_number",
                            rs.getString("contact_number"));
                    row.put("nic", rs.getString("nic"));
                    row.put("total_visits",
                            rs.getInt("total_visits"));
                    row.put("total_spent",
                            rs.getDouble("total_spent"));
                    data.add(row);
                }
                request.setAttribute("guestData", data);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("reportType", type);
        request.getRequestDispatcher("/reports.jsp")
                .forward(request, response);
    }
}
