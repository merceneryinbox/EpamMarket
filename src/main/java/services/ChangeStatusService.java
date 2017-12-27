package services;

import dao.PostgresUserDAO;
import dao.UserDAO;
import entities.User;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ChangeStatusService {

    //--------------------------------SINGLETON------------------------------------------

    private static ChangeStatusService instance = null;
    private static ChangeStatusService testInstance;
    private UserDAO userDao;

    private ChangeStatusService(UserDAO userDAO) {
        this.userDao = userDAO;
    }

    synchronized public static ChangeStatusService getInstance() {
        if (instance == null) { instance = new ChangeStatusService(PostgresUserDAO.getInstance()); }
        log.info("\nCUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId() + ""
                 + " \n"
                 + "and ThreadName = " + Thread.currentThread().getName()
                 + "\nmessage is\nChangeStatusService instance got " + instance.toString());
        return instance;
    }

    //--------------------------------------------------------------------------

    synchronized public static ChangeStatusService getTestInstance() {
        if (testInstance == null) {
            testInstance = new ChangeStatusService(PostgresUserDAO.getTestInstance());
        }

        return testInstance;
    }

    synchronized public void changeStatusById(int id) {
        User       newUser;
        User       oldUser;
        UserStatus status;
        if (userDao.getUserById(id).isPresent()) {
            oldUser = userDao.getUserById(id).get();
            newUser = oldUser;
            status = UserStatus.valueOf(newUser.getStatus());
            switch (status) {
                case BANNED:
                    newUser.setStatus("ACTIVE");
                    log.info("\nCUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId() + ""
                             + " \n"
                             + "and ThreadName = " + Thread.currentThread().getName()
                             + "\nmessage is\nUser status changed from " + oldUser.getStatus()
                             + " to " + newUser.getStatus());
                    break;
                case ACTIVE:
                    newUser.setStatus("BANNED");
                    log.info("\nCUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId() + ""
                             + " \n"
                             + "and ThreadName = " + Thread.currentThread().getName()
                             + "\nmessage is\nUser status changed from " + oldUser.getStatus()
                             + " to " + newUser.getStatus());
                    break;
                case ADMIN:
                    log.info("\nCUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId() + ""
                             + " \n"
                             + "and ThreadName = " + Thread.currentThread().getName()
                             + "\nmessage is\nUser status changed from " + oldUser.getStatus()
                             + " to " + newUser.getStatus());
                    break;
            }
            userDao.updateUser(newUser, oldUser);
        }
    }

}
