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
    private static AdminService testInstance;
    private UserDAO userDao;

    private AdminService(UserDAO userDAO) {
        this.userDao = userDAO;
        log.info(" CUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId() + ""
                 + " \n"
                 + "and ThreadName = " + Thread.currentThread().getName()
                 + "\nmessage is\nAdminService instance created.");
    }

    synchronized public static AdminService getInstance() {
        if (instance == null) { instance = new AdminService(PostgresUserDAO.getInstance()); }
        log.info(" CUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId() + ""
                 + " \n"
                 + "and ThreadName = " + Thread.currentThread().getName()
                 + "\nmessage is\nAdminService got " + instance.toString());
        return instance;
    }

    //--------------------------------------------------------------------------

    synchronized public static AdminService getTestInstance() {
        if (testInstance == null) {
            testInstance = new AdminService(PostgresUserDAO.getTestInstance());
        }
        return testInstance;
    }

    synchronized public List<User> getUserList() {
        List<User> users = userDao.getAllUsers();
        users.sort(null);
        log.info(" CUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId() + ""
                 + " \n"
                 + "and ThreadName = " + Thread.currentThread().getName()
                 + "\nmessage is\nUsers list got " + users.toString());
        return users;
    }

}

