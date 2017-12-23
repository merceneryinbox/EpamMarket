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
    public static final String GET_ALL_QUERY = "SELECT * FROM cart WHERE user_id = ?";
    public static final String GET_QUERY = "SELECT * FROM cart WHERE user_id = ? AND goods_id = ?";
    public static final String CREATE_QUERY = "INSERT INTO cart (user_id, goods_id, amount, reserve_time) VALUES (?,?,?,?)";
    public static final String UPDATE_QUERY = "UPDATE cart SET amount = ?, reserve_time = ? WHERE user_id = ? AND goods_id = ?";
    public static final String DELETE_QUERY = "DELETE FROM cart WHERE user_id = ? AND goods_id = ?";

    public final DataSource DATA_SOURCE;

    @Override
    public Optional<List<Reserve>> getReserveListByLogin(Integer userId) {
        ResultSet resultSet = null;
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY)) {
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
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
            return Optional.ofNullable(reserveList);
        } catch (SQLException e) {
            log.error("Droped down " + this.getClass().getCanonicalName() + " because of \n" + e
                    .getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Reserve> getReserveById(Integer reserveId) {
        return Optional.empty();
    }

    @Override
    public Optional<Reserve> getReserve(Integer userId, Integer goodId) {
        ResultSet resultSet = null;
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_QUERY)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, goodId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                val reserve = new Reserve(
                        resultSet.getInt("cart_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("goods_id"),
                        resultSet.getInt("amount"),
                        resultSet.getTimestamp("reserve_time")
                );
                return Optional.ofNullable(reserve);
            }
        } catch (SQLException e) {
            log.error("Droped down " + this.getClass().getCanonicalName() + " because of \n" + e
                    .getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void setAmountByLoginAndGoodId(Integer userId, Integer goodId, Integer amount) {
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
    public void deleteReserve(Integer userId, Integer goodID) {
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, goodID);
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error("Droped down " + this.getClass().getCanonicalName() + " because of \n" + e
                    .getMessage());
        }
    }

    private void createReserve(Integer userId, Integer goodId, Integer amount, Timestamp timestamp) {
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, goodId);
            preparedStatement.setInt(3, amount);
            preparedStatement.setTimestamp(4, timestamp);
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error("Droped down " + this.getClass().getCanonicalName() + " because of \n" + e
                    .getMessage());
        }
    }

    private void updateReserve(Integer userId, Integer goodId, Integer amount, Timestamp timestamp) {
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setInt(1, amount);
            preparedStatement.setTimestamp(2, timestamp);
            preparedStatement.setInt(3, userId);
            preparedStatement.setInt(4, goodId);
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error("Droped down " + this.getClass().getCanonicalName() + " because of \n" + e
                    .getMessage());
        }
    }

    public PostgresCartDAO() {
        this(DataSourceInit.getDataSource());
    }

    public PostgresCartDAO(DataSource dataSource) {
        DATA_SOURCE = dataSource;
    }
}
