package services;

import dao.PostgresUserDAO;
import dao.UserDAO;
import entities.User;

import java.util.Optional;

public class UserCheckPasswordService {

    public static User checkPassword(String login, String password) {
        User user = null;
        UserDAO userDAO = new PostgresUserDAO();
        Optional<User> userOptional = userDAO.getUserByLogin(login);
        if (userOptional.isPresent()) {
            user = userOptional.get();
            if (!(user.getPassword().equals(password))) {
                user = null;
            }
        }
        return user;
    }
}
