package dao;

import DbConnection.DataSourceInit;
import entities.Reserve;
import lombok.val;

import javax.sql.DataSource;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostgresCartDAO implements CartDAO {
    public static final String GET_ALL_QUERY = "SELECT * FROM cart WHERE user_id = ?";
    public static final String GET_QUERY = "SELECT * FROM cart WHERE user_id = ? and goods_id = ?";
    public static final String CREATE_QUERY = "INSERT INTO cart (user_id, goods_id, amount, reserve_time) VALUES (?,?,?,?)";
    public static final String UPDATE_QUERY = "UPDATE cart SET amount = ?, reserve_time = ? WHERE user_id = ? and goods_id = ?";
    public static final String DELETE_QUERY = "DELETE FROM cart WHERE user_id = ? and goods_id = ?";

    @Override
    public Optional<List<Reserve>> getReserveListByLogin(Integer login) {
        DataSource instance = DataSourceInit.getDataSource();
        try (Connection connection = instance.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY)) {
            preparedStatement.setInt(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
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
            return Optional.of(reserveList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Reserve> getReserve(Integer login, Integer goodId) {
        DataSource instance = DataSourceInit.getDataSource();
        try (Connection connection = instance.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_QUERY)) {
            preparedStatement.setInt(1, login);
            preparedStatement.setInt(2, goodId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                val reserve = new Reserve(
                        resultSet.getInt("cart_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("goods_id"),
                        resultSet.getInt("amount"),
                        resultSet.getTimestamp("reserve_time")
                );
                return Optional.of(reserve);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void setAmountByLoginAndGoodId(Integer login, Integer goodId, Integer amount) {
        if (amount == 0) {
            deleteReserve(login, goodId);
            return;
        }

        val reserve = getReserve(login, goodId);
        if (reserve.isPresent())
            updateReserve(login, goodId, amount, reserve.get().getReserveTime());
        else
            createReserve(login, goodId, amount, Timestamp.from(Instant.now()));
    }

    private void deleteReserve(Integer userId, Integer goodID) {
        DataSource instance = DataSourceInit.getDataSource();
        try (Connection connection = instance.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, goodID);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createReserve(Integer userId, Integer goodId, Integer amount, Timestamp timestamp) {
        DataSource instance = DataSourceInit.getDataSource();
        try (Connection connection = instance.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, goodId);
            preparedStatement.setInt(3, amount);
            preparedStatement.setTimestamp(4, timestamp);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateReserve(Integer userId, Integer goodId, Integer amount, Timestamp timestamp) {
        DataSource instance = DataSourceInit.getDataSource();
        try (Connection connection = instance.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setInt(1, amount);
            preparedStatement.setTimestamp(2, timestamp);
            preparedStatement.setInt(3, userId);
            preparedStatement.setInt(4, goodId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
