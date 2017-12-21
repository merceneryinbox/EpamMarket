package services;

import dao.UserDAO;
import dao.PostgresUserDAO;
import entities.User;

import java.util.ArrayList;
import java.util.List;

public class AdminService {
    private UserDAO userDAO;

    public AdminService() {
        userDAO = new PostgresUserDAO();
    }

    public List<User> getUserList() {
        List<User> userlist = new ArrayList<>();
        userlist = userDAO.getAllUsers();
        return userlist;
    }
}
