package dao;

import DbConnection.DataSourceInit;
import entities.Good;
import lombok.val;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PostgresGoodDAO implements GoodDAO {
    @Override
    public Optional<Good> getGoodByName(String name) {
        val sql = "SELECT * FROM goods WHERE product_name = '" + name + "'";
        DataSource instance = DataSourceInit.getDataSource();
        try (Connection connection = instance.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                val good = buildGoodFromResultSet(resultSet);
                return Optional.of(good);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void addGood(Good good) {
        val sql = "INSERT INTO goods (product_name, price, amount, description) VALUES (?,?,?,?)";
        DataSource instance = DataSourceInit.getDataSource();
        try (Connection connection = instance.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, good.getName());
            preparedStatement.setDouble(2, good.getPrice());
            preparedStatement.setInt(3, good.getAmount());
            preparedStatement.setString(4, good.getDescription());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteGoodByName(String name) {
        val sql = "DELETE FROM goods WHERE product_name = '" + name + "'";
        DataSource instance = DataSourceInit.getDataSource();
        try (Connection connection = instance.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateGood(Good good) {
        val sql = "UPDATE goods SET price = ?, amount = ?, description = ? WHERE product_name = ?";
        DataSource instance = DataSourceInit.getDataSource();
        try (Connection connection = instance.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, good.getPrice());
            preparedStatement.setInt(2, good.getAmount());
            preparedStatement.setString(3, good.getDescription());
            preparedStatement.setString(4, good.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Good buildGoodFromResultSet(ResultSet resultSet) throws SQLException {
        val good = new Good();
        good.setId(resultSet.getInt("id_good"));
        good.setName(resultSet.getString("product_name"));
        good.setPrice(resultSet.getDouble("price"));
        good.setAmount(resultSet.getInt("amount"));
        good.setDescription(resultSet.getString("description"));
        return good;
    }
}
