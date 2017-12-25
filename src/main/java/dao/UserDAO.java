package dao;

import entities.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO {

    boolean addUser(User user);

    Optional<User> getUserById(Integer id);

    Optional<User> getUserByLogin(String login);

    boolean updateUser(User newUser, User oldUser);

    boolean deleteUserByLogin(String login);

    boolean deleteUserById(Integer id);

    List<User> getAllUsers();
}
