package com.oceanview.servlet;

import com.oceanview.service.AuthService;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    private final AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        authService.logout(request);
        response.sendRedirect(
                request.getContextPath() + "/login.jsp");
    }
}
