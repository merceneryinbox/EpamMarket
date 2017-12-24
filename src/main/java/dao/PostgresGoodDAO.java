package dao;

import entities.Good;
import db.DataSourceInit;
import lombok.extern.log4j.Log4j2;
import lombok.val;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public class PostgresGoodDAO implements GoodDAO {

    //--------------------------------SINGLETON------------------------------------------

    private static PostgresGoodDAO instance = null;

    public static PostgresGoodDAO getInstance() {
        if (instance == null)
            instance = new PostgresGoodDAO(DataSourceInit.getPostgres());

        return instance;
    }

    private static PostgresGoodDAO testInstance;

    public static PostgresGoodDAO getTestInstance() {
        if (testInstance == null)
            testInstance = new PostgresGoodDAO(DataSourceInit.getH2());

        return testInstance;
    }

    //--------------------------------DATA-SOURCE---------------------------------------------

    final DataSource DATA_SOURCE;

    //--------------------------------QUERIES---------------------------------------------

    public static final String GET_QUERY =
            "SELECT * FROM goods WHERE name = ?";
    public static final String GET_ALL_QUERY =
            "SELECT * FROM goods";
    public static final String GET_BY_ID_QUERY =
            "SELECT * FROM goods WHERE goods_id = ?";
    public static final String ADD_QUERY =
            "INSERT INTO goods (name, price, amount, description) VALUES (?,?,?,?)";
    public static final String UPDATE_QUERY =
            "UPDATE goods SET price = ?, amount = ?, description = ? WHERE name = ?";
    public static final String DELETE_QUERY =
            "DELETE FROM goods WHERE name = ?";

    //--------------------------------CONSTRUCTOR---------------------------------------------

    private PostgresGoodDAO(DataSource dataSource) {
        DATA_SOURCE = dataSource;
    }

    //----------------------------------------------------------------------------------

    @Override
    public Optional<Good> getGoodById(Integer id) {
        ResultSet resultSet = null;
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY);) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
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
            log.error("Droped down " + this.getClass().getCanonicalName() + " because of \n" + e
                    .getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Good> getGoodByName(String name) {
        ResultSet resultSet = null;
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_QUERY);) {
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                val good = new Good(
                        resultSet.getInt("goods_id"),
                        // TODO Shefer 19.12 : Im not sure but `name` can be kinda keyword in SQL and should be escaped
                        // TODO Updated - no troubles have been detected while testing, so mb its ok
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("amount"),
                        resultSet.getString("description")
                );
                return Optional.ofNullable(good);
            }
        } catch (SQLException e) {
            log.error("Droped down " + this.getClass().getCanonicalName() + " because of \n" + e
                    .getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Good> getAllGoods() {
        List<Good> goods = new ArrayList<>();
        ResultSet resultSet = null;
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY);) {
            resultSet = preparedStatement.executeQuery();
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
            log.error("Droped down " + this.getClass().getCanonicalName() + " because of \n" + e
                    .getMessage());
        }
        return goods;
    }

    @Override
    public void addGood(Good good) {

        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_QUERY)) {
            preparedStatement.setString(1, good.getName());
            preparedStatement.setDouble(2, good.getPrice());
            preparedStatement.setInt(3, good.getAmount());
            preparedStatement.setString(4, good.getDescription());
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error("Droped down " + this.getClass().getCanonicalName() + " because of \n" + e
                    .getMessage());
        }
    }

    @Override
    public void deleteGoodByName(String name) {

        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setString(1, name);
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error("Droped down " + this.getClass().getCanonicalName() + " because of \n" + e
                    .getMessage());
        }
    }

    @Override
    public void updateGood(Good good) {

        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setDouble(1, good.getPrice());
            preparedStatement.setInt(2, good.getAmount());
            preparedStatement.setString(3, good.getDescription());
            preparedStatement.setString(4, good.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error("Droped down " + this.getClass().getCanonicalName() + " because of \n" + e
                    .getMessage());
        }
    }

}
