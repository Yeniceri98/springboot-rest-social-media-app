package com.demo.application.dao;

import com.demo.application.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> findAllUsers();

    User findSingleUserById(int id);

    User save(User user);

    void deleteUserById(int id);
}
