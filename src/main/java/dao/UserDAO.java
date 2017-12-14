package dao;

import entities.User;

import java.util.Optional;

public interface UserDAO {

    boolean createNew(User user);

    Optional<User> getUser(User user);

    boolean updateUser(User newUser, User oldUser);

    boolean deleteUser(User user);
}
