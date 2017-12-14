package dao;

import entities.User;

import java.util.Optional;

public class PostgresUserDAO implements UserDAO {
    @Override
    public boolean createNew(User user) {
        throw new UnsupportedOperationException("implement me");
    }

    @Override
    public Optional<User> getUser(User user) {
        throw new UnsupportedOperationException("implement me");
    }

    @Override
    public boolean updateUser(User newUser, User oldUser) {
        throw new UnsupportedOperationException("implement me");
    }

    @Override
    public boolean deleteUser(User user) {
        throw new UnsupportedOperationException("implement me");
    }
}
