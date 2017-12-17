package dao;

import DbConnection.DataSourceInit;
import entities.Good;
import entities.Reserve;
import lombok.val;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostgresCartDao implements CartDAO {
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
                        resultSet.getString("reserve_time")
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
    public Optional<Reserve> gerReserve(String login, Integer goodId) {
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
                        resultSet.getString("reserve_time")
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
        val reserve = gerReserve(login, goodId);
        String reserve_time = System.currentTimeMillis() + "";
        if (reserve.isPresent()) reserve_time = reserve.get().getReserveTime();
        val sql = "INSERT INTO goods (id_user, id_good, amount_cart, reserve_time) VALUES (?,?,?,?)";
        DataSource instance = DataSourceInit.getDataSource();
        try (Connection connection = instance.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            preparedStatement.setDouble(2, goodId);
            preparedStatement.setInt(3, amount);
            preparedStatement.setString(4, reserve_time);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
