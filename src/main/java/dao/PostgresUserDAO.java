package dao;

import entities.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class PostgresUserDAO implements UserDAO {
    @Override
    public boolean createNew(User user) {
        throw new UnsupportedOperationException("implement me");
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        throw new UnsupportedOperationException("implement me");
    }

    @Override
    public boolean updateUser(User newUser, User oldUser) {
        throw new UnsupportedOperationException("implement me");
    }

    @Override
    public boolean deleteUserByLogin(String login) {
        throw new UnsupportedOperationException("implement me");
    }
}
