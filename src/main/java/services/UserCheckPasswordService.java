package services;

import dao.PostgresUserDAO;
import dao.UserDAO;
import entities.User;
import lombok.extern.log4j.Log4j2;

import java.util.Optional;

@Log4j2
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
