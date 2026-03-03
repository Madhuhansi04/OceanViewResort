package com.oceanview.service.interfaces;

import com.oceanview.model.User;

public interface IAuthService {
    User login(String username, String password);
    void logout(javax.servlet.http.HttpServletRequest request);
}
