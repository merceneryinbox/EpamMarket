package services;

import dao.PostgresUserDAO;
import dao.UserDAO;
import entities.User;

import java.util.List;

public class AdminService {

    //--------------------------------SINGLETON------------------------------------------

    private static AdminService instance = null;

    public static AdminService getInstance() {
        if (instance == null)
            instance = new AdminService(PostgresUserDAO.getInstance());
        return instance;
    }

    private static AdminService testInstance;

    public static AdminService getTestInstance() {
        if (testInstance == null)
            testInstance = new AdminService(PostgresUserDAO.getTestInstance());
        return testInstance;
    }

    //--------------------------------CONSTRUCTOR------------------------------------------

    private AdminService(UserDAO userDAO) {
        this.userDao = userDAO;
    }

    //--------------------------------------------------------------------------
    private UserDAO userDao;

    public List<User> getUserList() {
        return userDao.getAllUsers();
    }
}

