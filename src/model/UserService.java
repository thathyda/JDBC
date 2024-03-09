package model;

import java.util.ArrayList;
import java.util.List;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User searchByID(Integer id);
    void insertUser(User user);
    void deleteUser(Integer id);
    void updateUser(User user);
}

