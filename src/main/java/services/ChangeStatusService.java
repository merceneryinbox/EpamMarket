package services;

import dao.PostgresUserDAO;
import dao.UserDAO;
import entities.User;

public class ChangeStatusService {

    public static void changeStatusById(int id) {
        UserDAO userDAO = new PostgresUserDAO();
        User newUser;
        User oldUser;
        UserStatus status;
        if (userDAO.getUserById(id).isPresent()) {
            oldUser = userDAO.getUserById(id).get();
            newUser = oldUser;
            status = UserStatus.valueOf(newUser.getStatus());
            switch (status) {
                case BANNED:
                    newUser.setStatus("ACTIVE");
                    break;
                case ACTIVE:
                    newUser.setStatus("BANNED");
                    break;
                case ADMIN:
                    break;
            }
            boolean check = userDAO.updateUser(newUser,oldUser);
        }
    }

}
