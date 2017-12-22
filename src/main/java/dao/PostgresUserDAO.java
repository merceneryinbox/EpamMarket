package dao;

import DbConnection.DataSourceInit;
import entities.User;
import lombok.extern.log4j.Log4j2;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Log4j2
public class PostgresUserDAO implements UserDAO {

    //--------------------------------SINGLETON------------------------------------------

    private static PostgresUserDAO instance = null;

    public static PostgresUserDAO getInstance() {
        if (instance == null)
            instance = new PostgresUserDAO(DataSourceInit.getPostgres());

        return instance;
    }

    private static PostgresUserDAO testInstance;

    public static PostgresUserDAO getTestInstance() {
        if (testInstance == null)
            testInstance = new PostgresUserDAO(DataSourceInit.getH2());

        return testInstance;
    }

    //--------------------------------DATA-SOURCE---------------------------------------------

    private DataSource DATA_SOURCE;

    //--------------------------------QUERIES---------------------------------------------

    private static final String INSERT_QUERY_NEW =
            "INSERT INTO users (login,email,phone,password,status) VALUES(?,?,?,?,?)";
    private static final String SELECT_QUERY_BY_LOGIN =
            "SELECT * FROM users WHERE login = ?";
    private static final String SELECT_QUERY_BY_ID =
            "SELECT * FROM users WHERE user_id = ?";
    private static final String UPDATE_QUERY =
            "UPDATE users SET login=?,email=?, phone=?,password=?,status=? WHERE user_id=?";
    private static final String DELETE_QUERY_BY_LOGIN =
            "DELETE FROM users WHERE login = ?";
    private static final String DELETE_QUERY_BY_ID =
            "DELETE FROM users WHERE user_id = ?";

    //--------------------------------CONSTRUCTOR---------------------------------------------

    private PostgresUserDAO(DataSource DATA_SOURCE) {
        this.DATA_SOURCE = DATA_SOURCE;
    }

    //--------------------------------DAO-METHODS---------------------------------------------

    @Override
    public boolean createNew(User user) {
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY_NEW)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getStatus());
            preparedStatement.execute();
            log.info(
                    "Successfully creating public boolean createNew(User user) in PostgresUserDAO");
            return true;
        } catch (SQLException e) {
            log.error("Droped down " + this.getClass().getCanonicalName() + " because of \n" + e
                    .getMessage());
            return false;
        }

    }

    @Override
    public Optional<User> getUserById(Integer id) {
        User user;
        try (Connection connection = DATA_SOURCE.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            user = parserResultSet(resultSet);
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            log.error("Droped down " + this.getClass().getCanonicalName() + " because of \n" + e
                    .getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        User user;
        try (Connection connection = DATA_SOURCE.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            user = parserResultSet(resultSet);
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            log.error("Droped down " + this.getClass().getCanonicalName() + " because of \n" + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public boolean updateUser(User newUser, User oldUser) {
        //FIXME do we need check if newUser or oldUser is null? - yes we do !
        try (Connection connection = DATA_SOURCE.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setString(1, newUser.getLogin());
            preparedStatement.setString(2, newUser.getEmail());
            preparedStatement.setString(3, newUser.getPhone());
            preparedStatement.setString(4, newUser.getPassword());
            preparedStatement.setString(5, newUser.getStatus());
            preparedStatement.setInt(6, oldUser.getId());
            preparedStatement.execute();
            //TODO info in log4j
            log.info("User successfully updated by updateUser method in PostgresUserDao !");
            return true;
        } catch (SQLException e) {
            log.error("Droped down " + this.getClass().getCanonicalName() + " because of \n" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteUserByLogin(String login) {
        try (Connection connection = DATA_SOURCE.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            preparedStatement.execute();
            //TODO info in log4j
            log.info("User successfully delete by deleteUserByLogin method in PostgresUserDao !");
            return true;
        } catch (SQLException e) {
            log.error("Droped down " + this.getClass().getCanonicalName() + " because of \n" + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteUserById(Integer id) {
        try (Connection connection = DATA_SOURCE.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            //TODO info in log4j
            log.info("User successfully delete by deleteUserById method in PostgresUserDao !");
            return true;
        } catch (SQLException e) {
            log.error("Dropped down " + this.getClass().getCanonicalName() + " because of \n" + e
                    .getMessage());
            return false;
        }
    }

    //--------------------------------OTHER-METHODS---------------------------------------------

    private User parserResultSet(ResultSet resultSet) {
        User user = new User();
        try {
            if (resultSet.next()) {
                String login = resultSet.getString("login");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String password = resultSet.getString("password");
                String status = resultSet.getString("status");
                int id = resultSet.getInt("user_id");
                user.setLogin(login);
                user.setId(id);
                user.setEmail(email);
                user.setPhone(phone);
                user.setPassword(password);
                user.setStatus(status);
            } else {
                user = null;
            }
        } catch (SQLException e) {
            log.error("Dropped down " + this.getClass().getCanonicalName() + " because of \n" + e.getMessage());
        }
        return user;
    }
}