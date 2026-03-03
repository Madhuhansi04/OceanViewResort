package com.oceanview.service;

import com.oceanview.dao.UserDAO;
import com.oceanview.model.User;
import com.oceanview.service.interfaces.IAuthService;
import com.oceanview.util.SessionUtil;
import javax.servlet.http.HttpServletRequest;

public class AuthService implements IAuthService {

    private final UserDAO userDAO = new UserDAO();

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() ||
                password == null || password.isEmpty()) {
            return null;
        }
        return userDAO.authenticate(username, password);
    }

    @Override
    public void logout(HttpServletRequest request) {
        SessionUtil.invalidate(request);
    }
}
