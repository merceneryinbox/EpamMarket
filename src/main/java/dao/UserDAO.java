package dao;

import entities.User;

import java.util.Optional;

public interface UserDAO {

    boolean createNew(User user);

    Optional<User> getUserById(Integer id);

    Optional<User> getUserByLogin(String login);

    boolean updateUser(User newUser, User oldUser);

    boolean deleteUserByLogin(String login);

    boolean deleteUserById(Integer id);
}
