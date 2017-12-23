package services;

import dao.PostgresUserDAO;
import dao.UserDAO;
import entities.User;

import java.util.List;

public class AdminService {

    public static List<User> getUserList() {
        UserDAO userDAO = new PostgresUserDAO();
        List<User> userList;
        userList = userDAO.getAllUsers();
        return userList;
    }
}
