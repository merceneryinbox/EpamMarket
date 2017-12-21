package dao;

import DbConnection.DataSourceInit;
import entities.Good;
import lombok.val;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostgresGoodDAO implements GoodDAO {
    public static final String GET_QUERY = "SELECT * FROM goods WHERE name = ?";
    public static final String GET_ALL_QUERY = "SELECT * FROM goods";
    public static final String ADD_QUERY = "INSERT INTO goods (name, price, amount, description) VALUES (?,?,?,?)";
    public static final String UPDATE_QUERY = "UPDATE goods SET price = ?, amount = ?, description = ? WHERE name = ?";
    public static final String DELETE_QUERY = "DELETE FROM goods WHERE name = ?";

    @Override
    public Optional<Good> getGoodById(Integer id) {
        DataSource instance = DataSourceInit.getDataSource();
        try (Connection connection = instance.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_QUERY);) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                val good = new Good(
                        resultSet.getInt("goods_id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("amount"),
                        resultSet.getString("description")
                );
                return Optional.ofNullable(good);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Good> getGoodByName(String name) {
        DataSource instance = DataSourceInit.getDataSource();
        try (Connection connection = instance.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_QUERY);) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                val good = new Good(
                        resultSet.getInt("goods_id"),
                        // TODO Shefer 19.12 : Im not sure but `name` can be kinda keyword in SQL and should be escaped
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("amount"),
                        resultSet.getString("description")
                );
                return Optional.ofNullable(good);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Good> getAllGoods() {
        List<Good> goods = new ArrayList<>();
        DataSource instance = DataSourceInit.getDataSource();
        try (Connection connection = instance.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY);) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                goods.add(new Good(
                        resultSet.getInt("goods_id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("amount"),
                        resultSet.getString("description")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

    @Override
    public void addGood(Good good) {
        DataSource instance = DataSourceInit.getDataSource();
        try (Connection connection = instance.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_QUERY)) {
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
        DataSource instance = DataSourceInit.getDataSource();
        try (Connection connection = instance.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setString(1, name);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateGood(Good good) {
        DataSource instance = DataSourceInit.getDataSource();
        try (Connection connection = instance.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setDouble(1, good.getPrice());
            preparedStatement.setInt(2, good.getAmount());
            preparedStatement.setString(3, good.getDescription());
            preparedStatement.setString(4, good.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
