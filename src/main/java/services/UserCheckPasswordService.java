package services;

import dao.PostgresUserDAO;
import dao.UserDAO;
import entities.User;
import lombok.extern.log4j.Log4j2;

import java.util.Optional;

@Log4j2
public class UserCheckPasswordService {

    //--------------------------------SINGLETON------------------------------------------

    private static UserCheckPasswordService instance = null;

    public static UserCheckPasswordService getInstance() {
        if (instance == null)
            instance = new UserCheckPasswordService(PostgresUserDAO.getInstance());
        return instance;
    }

    private static UserCheckPasswordService testInstance;

    public static UserCheckPasswordService getTestInstance() {
        if (testInstance == null)
            testInstance = new UserCheckPasswordService(PostgresUserDAO.getTestInstance());
        return testInstance;
    }

    //--------------------------------CONSTRUCTOR------------------------------------------

    private UserCheckPasswordService(UserDAO userDAO) {
        this.userDao = userDAO;
    }

    //-------------------------------------------------------------------------------

    private UserDAO userDao;

    public User checkPassword(String login, String password) {
        Optional<User> user = userDao.getUserByLogin(login);
        if (user.isPresent() && (user.get().getPassword().equals(password))) {
            return user.get();
        }
        return null;
    }
}
