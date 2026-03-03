package com.oceanview.util;

import com.oceanview.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
    public static void setUser(HttpServletRequest request, User user) {
        HttpSession session = request.getSession(true);
        session.setAttribute("loggedUser", user);
        session.setMaxInactiveInterval(30 * 60);
    }

    public static User getUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (User) session.getAttribute("loggedUser");
        }
        return null;
    }

    public static boolean isLoggedIn(HttpServletRequest request) {
        return getUser(request) != null;
    }

    public static void invalidate(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
