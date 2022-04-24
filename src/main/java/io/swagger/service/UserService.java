package io.swagger.service;

import io.swagger.model.User;

import java.util.List;

public interface UserService {
    public User addUser(User User);
    public User deleteUserById(long rollNo);
    public List<User> findAllUsers();
    public User findUserById(long id);
    public User findUserByEmail(String email);
}
