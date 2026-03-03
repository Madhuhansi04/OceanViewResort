package com.oceanview.servlet;

import com.oceanview.model.User;
import com.oceanview.service.AuthService;
import com.oceanview.util.SessionUtil;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private final AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = authService.login(username, password);

        if (user != null) {
            SessionUtil.setUser(request, user);
            response.sendRedirect(
                    request.getContextPath() + "/DashboardServlet");
        } else {
            request.setAttribute("error",
                    "Invalid username or password!");
            request.getRequestDispatcher("/login.jsp")
                    .forward(request, response);
        }
    }
}

