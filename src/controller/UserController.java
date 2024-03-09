package controller;

import model.User;
import model.UserService;
import model.UserServiceImp;

import java.util.List;

public class UserController {
    private final UserService userService = new UserServiceImp();
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    public User searchUserByID(Integer id){
        return userService.searchByID(id);
    }
    public void insertUser(User user) {
        userService.insertUser(user);
    }
    public void deleteUser(Integer id){
        userService.deleteUser(id);
    }
    public void updateUser(User user){
        userService.updateUser(user);
    }
}
