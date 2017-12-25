package services;

import dao.PostgresUserDAO;
import dao.UserDAO;
import entities.User;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ChangeStatusService {

    //--------------------------------SINGLETON------------------------------------------

    private static ChangeStatusService instance = null;

    synchronized public static ChangeStatusService getInstance() {
        if (instance == null)
            instance = new ChangeStatusService(PostgresUserDAO.getInstance());
        log.info("ChangeStatusService instance got " + instance.toString());
        return instance;
    }

    private static ChangeStatusService testInstance;

    synchronized public static ChangeStatusService getTestInstance() {
        if (testInstance == null)
            testInstance = new ChangeStatusService(PostgresUserDAO.getTestInstance());
        return testInstance;
    }

    private ChangeStatusService(UserDAO userDAO) {
        this.userDao = userDAO;
    }

    //--------------------------------------------------------------------------

    private UserDAO userDao;

    synchronized public void changeStatusById(int id) {
        User newUser;
        User oldUser;
        UserStatus status;
        if (userDao.getUserById(id).isPresent()) {
            oldUser = userDao.getUserById(id).get();
            newUser = oldUser;
            status = UserStatus.valueOf(newUser.getStatus());
            switch (status) {
                case BANNED:
                    newUser.setStatus("ACTIVE");
                    log.info("User status changed from " + oldUser.getStatus() + " to " + newUser.getStatus());
                    break;
                case ACTIVE:
                    newUser.setStatus("BANNED");
                    log.info("User status changed from " + oldUser.getStatus() + " to " + newUser.getStatus());
                    break;
                case ADMIN:
                    log.info("User status changed from " + oldUser.getStatus() + " to " + newUser.getStatus());
                    break;
            }
            boolean check = userDao.updateUser(newUser, oldUser);
        }
    }

}
