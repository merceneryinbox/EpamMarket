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
    private static UserCheckPasswordService testInstance;
    private UserDAO userDao;

    private UserCheckPasswordService(UserDAO userDAO) {
        this.userDao = userDAO;
    }

    synchronized public static UserCheckPasswordService getInstance() {
        if (instance == null) {
            instance = new UserCheckPasswordService(PostgresUserDAO.getInstance());
        }
        log.info(" CUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId()
                 + "\nand ThreadName = " + Thread.currentThread().getName() + "\n " +
                 "message is\nInstance of singltone got.\n" + instance.toString());
        return instance;
    }

    //-------------------------------------------------------------------------------

    synchronized public static UserCheckPasswordService getTestInstance() {
        if (testInstance == null) {
            testInstance = new UserCheckPasswordService(PostgresUserDAO.getTestInstance());
        }
        return testInstance;
    }

    synchronized public User checkPassword(String login, String password) {
        Optional<User> user = userDao.getUserByLogin(login);
        if (user.isPresent() && (user.get().getPassword().equals(password))) {
            User user1 = user.get();
            log.info(" CUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId()
                     + "\nand ThreadName = " + Thread.currentThread().getName() + "\n " +
                     "message is\nLegal user " + user1.toString() + " detected + "
                     + getClass().getName());
            return user1;
        }
        log.error(" CUSTOM-ERROR-IN-ThreadID = \n" + Thread.currentThread().getId()
                  + "\nand ThreadName = " + Thread.currentThread().getName() + "\n " +
                  "message is\nNo user with such login = " + login + " and password " + password
                  + " in the DB detected. + " + getClass().getName());
        return null;
    }
}
