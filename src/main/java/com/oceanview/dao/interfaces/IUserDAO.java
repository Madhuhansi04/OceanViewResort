package com.oceanview.dao.interfaces;

import com.oceanview.model.User;

public interface IUserDAO {
    User authenticate(String username, String password);
    User getUserById(int id);

}
