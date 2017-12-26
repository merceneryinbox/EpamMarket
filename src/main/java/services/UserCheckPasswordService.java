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

    synchronized public static UserCheckPasswordService getInstance() {
        if (instance == null)
            instance = new UserCheckPasswordService(PostgresUserDAO.getInstance());
        log.info("Instance of singltone got.\n" + instance.toString());
        return instance;
    }

    private static UserCheckPasswordService testInstance;

    synchronized public static UserCheckPasswordService getTestInstance() {
        if (testInstance == null)
            testInstance = new UserCheckPasswordService(PostgresUserDAO.getTestInstance());
        return testInstance;
    }

    private UserCheckPasswordService(UserDAO userDAO) {
        this.userDao = userDAO;
    }

    //-------------------------------------------------------------------------------

    private UserDAO userDao;

    synchronized public User checkPassword(String login, String password) {
        Optional<User> user = userDao.getUserByLogin(login);
        if (user.isPresent() && (user.get().getPassword().equals(password))) {
            User user1 = user.get();
            log.info("Legal user " + user1.toString() + " detected + " + getClass().getName());
            return user1;
        }
        log.error("No user with such login = " + login + " and password " + password + " in the DB detected. + " + getClass().getName());
        return null;
    }
}
