package dao;

import db.DataSourceInit;
import entities.Reserve;
import lombok.extern.log4j.Log4j2;
import lombok.val;

import javax.sql.DataSource;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public class PostgresCartDAO implements CartDAO {

    //--------------------------------SINGLETON-----------------------------------------

    private static PostgresCartDAO instance = null;

    synchronized public static PostgresCartDAO getInstance() {
        if (instance == null)
            instance = new PostgresCartDAO(DataSourceInit.getPostgres());
        log.info("Instance of PostgresCartDAO got.");
        return instance;
    }

    private static PostgresCartDAO testInstance;

    synchronized public static PostgresCartDAO getTestInstance() {
        if (testInstance == null)
            testInstance = new PostgresCartDAO(DataSourceInit.getH2());
        return testInstance;
    }

    //--------------------------------QUERIES---------------------------------------------
    public static final String GET_ALL_QUERY = "SELECT * FROM cart";
    public static final String GET_ALL_BY_ID_QUERY = "SELECT * FROM cart WHERE user_id = ?";
    public static final String GET_QUERY = "SELECT * FROM cart WHERE user_id = ? AND goods_id = ?";
    public static final String CREATE_QUERY = "INSERT INTO cart (user_id, goods_id, amount, reserve_time) VALUES (?,?,?,?)";
    public static final String UPDATE_QUERY = "UPDATE cart SET amount = ?, reserve_time = ? WHERE user_id = ? AND goods_id = ?";
    public static final String DELETE_QUERY = "DELETE FROM cart WHERE user_id = ? AND goods_id = ?";


    public final DataSource DATA_SOURCE;

    //--------------------------------CONSTRUCTOR---------------------------------------

    private PostgresCartDAO(DataSource dataSource) {
        DATA_SOURCE = dataSource;
    }

    //--------------------------------DAO-METHODS--------------------------------------

    @Override
    synchronized public Optional<List<Reserve>> getReserveListByUserId(Integer userId) {
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_BY_ID_QUERY)) {
            preparedStatement.setInt(1, userId);
            try (val resultSet = preparedStatement.executeQuery()) {
                List<Reserve> reserveList = new ArrayList<>();
                while (resultSet.next()) {
                    val reserve = new Reserve(
                            resultSet.getInt("cart_id"),
                            resultSet.getInt("user_id"),
                            resultSet.getInt("goods_id"),
                            resultSet.getInt("amount"),
                            resultSet.getTimestamp("reserve_time")
                    );
                    reserveList.add(reserve);
                }
                log.info("Resrve list got by login " + userId);
                return Optional.ofNullable(reserveList);
            }
        } catch (SQLException e) {
            log.error("Dropped down " + this.getClass().getCanonicalName() + " because of \n" + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    synchronized public Optional<Reserve> getReserveById(Integer reserveId) {
        return Optional.empty();
    }

    @Override
    synchronized public Optional<Reserve> getReserve(Integer userId, Integer goodId) {
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_QUERY)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, goodId);
            try (val resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    val reserve = new Reserve(
                            resultSet.getInt("cart_id"),
                            resultSet.getInt("user_id"),
                            resultSet.getInt("goods_id"),
                            resultSet.getInt("amount"),
                            resultSet.getTimestamp("reserve_time")
                    );
                    log.info("Reserve got.");
                    return Optional.ofNullable(reserve);
                }
            }
        } catch (SQLException e) {
            log.debug("Dropped down " + this.getClass().getCanonicalName() + " because of \n" + e
                    .getMessage());
        }
        return Optional.empty();
    }

    @Override
    synchronized public void setAmountByLoginAndGoodId(Integer userId, Integer goodId, Integer amount) {
        if (amount == 0) {
            deleteReserve(userId, goodId);
            return;
        }
        val reserve = getReserve(userId, goodId);
        if (reserve.isPresent()) {
            updateReserve(userId, goodId, amount, reserve.get().getReserveTime());
        } else {
            createReserve(userId, goodId, amount, Timestamp.from(Instant.now()));
        }
    }

    @Override
    synchronized public Optional<List<Reserve>> getAllReserves() {
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Reserve> allReserves = new ArrayList<>();
            while (resultSet.next()) {
                val reserve = new Reserve(
                        resultSet.getInt("cart_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("goods_id"),
                        resultSet.getInt("amount"),
                        resultSet.getTimestamp("reserve_time")
                );
                allReserves.add(reserve);
            }
            log.info("Received all reserves");
            return Optional.ofNullable(allReserves);
        } catch (SQLException e) {
            log.debug("Dropped down " + this.getClass().getCanonicalName() + " because of \n" + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    synchronized public void deleteReserve(Integer userId, Integer goodID) {
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, goodID);
            preparedStatement.execute();
            log.info("Reserve deleted.");

        } catch (SQLException e) {
            log.debug("Dropped down " + this.getClass().getCanonicalName() + " because of \n" + e
                    .getMessage());
        }
    }

    synchronized private void createReserve(Integer userId, Integer goodId, Integer amount, Timestamp timestamp) {
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, goodId);
            preparedStatement.setInt(3, amount);
            preparedStatement.setTimestamp(4, timestamp);
            preparedStatement.execute();
            log.info("Reserve created.");
        } catch (SQLException e) {
            log.debug("Dropped down " + this.getClass().getCanonicalName() + " because of \n" + e
                    .getMessage());
        }
    }


    synchronized private void updateReserve(Integer userId, Integer goodId, Integer amount, Timestamp timestamp) {
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setInt(1, amount);
            preparedStatement.setTimestamp(2, timestamp);
            preparedStatement.setInt(3, userId);
            preparedStatement.setInt(4, goodId);
            preparedStatement.execute();
            log.info("Reserve updated.");
        } catch (SQLException e) {
            log.debug("Dropped down " + this.getClass().getCanonicalName() + " because of \n" + e
                    .getMessage());
        }
    }
}
