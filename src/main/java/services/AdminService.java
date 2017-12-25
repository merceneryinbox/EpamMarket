package services;

import dao.PostgresUserDAO;
import dao.UserDAO;
import entities.User;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class AdminService {

    //--------------------------------SINGLETON------------------------------------------

    private static AdminService instance = null;

    synchronized public static AdminService getInstance() {
        if (instance == null)
            instance = new AdminService(PostgresUserDAO.getInstance());
        log.info("AdminService got " + instance.toString());
        return instance;
    }

    private static AdminService testInstance;

    synchronized public static AdminService getTestInstance() {
        if (testInstance == null)
            testInstance = new AdminService(PostgresUserDAO.getTestInstance());
        return testInstance;
    }

    private AdminService(UserDAO userDAO) {
        this.userDao = userDAO;
    }

    //--------------------------------------------------------------------------

    private UserDAO userDao;

    synchronized public List<User> getUserList() {
        List<User> users = userDao.getAllUsers();
        users.sort(null);
        log.info("Users list got " + users.toString());
        return users;
    }

}

