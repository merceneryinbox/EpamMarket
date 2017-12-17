package dao;

import entities.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserDAO {

    boolean createNew(User user);

    Optional<User> getUserByLogin(String login);

    boolean updateUser(User newUser, User oldUser);

    boolean deleteUserByLogin(String login);
}
