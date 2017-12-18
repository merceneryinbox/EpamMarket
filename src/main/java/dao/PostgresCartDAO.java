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
    @Override
    public Optional<List<Reserve>> getReserveListByLogin(String login) {
        val sql = "SELECT * FROM cart WHERE id_user = '" + login + "'";
        DataSource instance = DataSourceInit.getDataSource();
        try (Connection connection = instance.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Reserve> reserveList = new ArrayList<>();
            while (resultSet.next()) {
                val reserve = new Reserve(
                        resultSet.getInt("id_cart"),
                        resultSet.getString("id_user"),
                        resultSet.getInt("id_good"),
                        resultSet.getInt("amount_cart"),
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
    public Optional<Reserve> getReserve(String login, Integer goodId) {
        val sql = "SELECT * FROM cart WHERE id_user = ? and id_good = ?";
        DataSource instance = DataSourceInit.getDataSource();
        try (Connection connection = instance.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            preparedStatement.setInt(2, goodId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                val reserve = new Reserve(
                        resultSet.getInt("id_cart"),
                        resultSet.getString("id_user"),
                        resultSet.getInt("id_good"),
                        resultSet.getInt("amount_cart"),
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
    public void setAmountByLoginAndGoodId(String login, Integer goodId, Integer amount) {
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

    private void deleteReserve(String login, Integer goodID) {
        val sql = "DELETE FROM cart WHERE id_user = ? and id_good = ?";
        DataSource instance = DataSourceInit.getDataSource();
        try (Connection connection = instance.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            preparedStatement.setInt(2, goodID);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createReserve(String login, Integer goodId, Integer amount, Timestamp timestamp) {
        val sql = "INSERT INTO cart (id_user, id_good, amount_cart, reserve_time) VALUES (?,?,?,?)";
        DataSource instance = DataSourceInit.getDataSource();
        try (Connection connection = instance.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            preparedStatement.setDouble(2, goodId);
            preparedStatement.setInt(3, amount);
            preparedStatement.setTimestamp(4, timestamp);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateReserve(String login, Integer goodId, Integer amount, Timestamp timestamp) {
        val sql = "UPDATE cart SET amount_cart = ?, reserve_time = ? WHERE id_user = ? and id_good = ?";
        DataSource instance = DataSourceInit.getDataSource();
        try (Connection connection = instance.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, amount);
            preparedStatement.setTimestamp(2, timestamp);
            preparedStatement.setString(3, login);
            preparedStatement.setDouble(4, goodId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
