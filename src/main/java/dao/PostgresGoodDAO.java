package dao;

import db.DataSourceInit;
import entities.Good;
import lombok.extern.log4j.Log4j2;
import lombok.val;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public class PostgresGoodDAO implements GoodDAO {

    //--------------------------------SINGLETON------------------------------------------

    public static final String GET_QUERY       =
            "SELECT * FROM goods WHERE name = ?";
    public static final String GET_ALL_QUERY   =
            "SELECT * FROM goods";
    public static final String GET_BY_ID_QUERY =
            "SELECT * FROM goods WHERE goods_id = ?";
    public static final String ADD_QUERY       =
            "INSERT INTO goods (name, price, amount, description) VALUES (?,?,?,?)";

    //--------------------------------DATA-SOURCE---------------------------------------------
    public static final String UPDATE_QUERY =
            "UPDATE goods SET price = ?, amount = ?, description = ? WHERE name = ?";

    //--------------------------------QUERIES---------------------------------------------
    public static final String          DELETE_QUERY =
            "DELETE FROM goods WHERE name = ?";
    private static      PostgresGoodDAO instance     = null;
    private static PostgresGoodDAO testInstance;
    final          DataSource      DATA_SOURCE;

    private PostgresGoodDAO(DataSource dataSource) {
        DATA_SOURCE = dataSource;
    }

    synchronized public static PostgresGoodDAO getInstance() {
        if (instance == null) { instance = new PostgresGoodDAO(DataSourceInit.getPostgres()); }
        log.info(" CUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId() + ""
                 + " \n"
                 + "and ThreadName = " + Thread.currentThread().getName()
                 + "\nmessage is\ninstance of PostgresGoodDAO created.");
        return instance;
    }

    //--------------------------------CONSTRUCTOR---------------------------------------------

    synchronized public static PostgresGoodDAO getTestInstance() {
        if (testInstance == null) { testInstance = new PostgresGoodDAO(DataSourceInit.getH2()); }
        // TODO: 25.12.2017 Why this produces exception? @OFedulov
//        log.info("Instance of PostgresGoodDAO got " + instance.toString());
        return testInstance;
    }

    //----------------------------------------------------------------------------------

    @Override
    synchronized public Optional<Good> getGoodById(Integer id) {
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY);) {
            preparedStatement.setInt(1, id);
            try (val resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    val good = new Good(
                            resultSet.getInt("goods_id"),
                            resultSet.getString("name"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("amount"),
                            resultSet.getString("description")
                    );
                    log.info(" CUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId() + ""
                             + " \n"
                             + "and ThreadName = " + Thread.currentThread().getName()
                             + "\nmessage is\nGood " + good.toString() + " got by ID = " + id
                             + " + " + getClass().getName());
                    return Optional.ofNullable(good);
                }
            }
        } catch (SQLException e) {
            log.debug(" CUSTOM-DEBUG-IN-ThreadID = \n" + Thread.currentThread().getId() + ""
                      + " \n"
                      + "and ThreadName = " + Thread.currentThread().getName()
                      + "\nmessage is\nDropped down " + this.getClass().getCanonicalName()
                      + " because of \n" + e
                              .getMessage() + " + " + getClass().getName());
        }
        return Optional.empty();
    }

    @Override
    synchronized public Optional<Good> getGoodByName(String name) {
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_QUERY);) {
            preparedStatement.setString(1, name);
            try (val resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    val good = new Good(
                            resultSet.getInt("goods_id"),
                            // TODO Shefer 19.12 : Im not sure but `name` can be kinda keyword in
                            // SQL and should be escaped
                            // TODO Updated - no troubles have been detected while testing, so mb
                            // its ok
                            resultSet.getString("name"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("amount"),
                            resultSet.getString("description")
                    );
                    log.info("Good " + good.toString() + " got by name " + name + " + " + getClass()
                            .getName());
                    return Optional.ofNullable(good);
                }
            }
        } catch (SQLException e) {
            log.debug("Dropped down " + this.getClass().getCanonicalName() + " because of \n" + e
                    .getMessage() + " + " + getClass().getName());
        }
        return Optional.empty();
    }

    @Override
    synchronized public List<Good> getAllGoods() {
        List<Good> goods = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY);
             val resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                goods.add(new Good(
                        resultSet.getInt("goods_id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("amount"),
                        resultSet.getString("description")
                ));
            }
            log.info(" CUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId() + ""
                     + " \n"
                     + "and ThreadName = " + Thread.currentThread().getName()
                     + "\nmessage is\nAll Goods List got + " + getClass().getName());
        } catch (SQLException e) {
            log.debug(" CUSTOM-DEBUG-IN-ThreadID = \n" + Thread.currentThread().getId() + ""
                      + " \n"
                      + "and ThreadName = " + Thread.currentThread().getName()
                      + "\nmessage is\nDroped down " + this.getClass().getCanonicalName()
                      + " because of \n"
                      + e.getMessage() + " + " + getClass().getName());
        }
        return goods;
    }

    @Override
    synchronized public void addGood(Good good) {

        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_QUERY)) {
            preparedStatement.setString(1, good.getName());
            preparedStatement.setDouble(2, good.getPrice());
            preparedStatement.setInt(3, good.getAmount());
            preparedStatement.setString(4, good.getDescription());
            preparedStatement.execute();
            log.info(" CUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId() + ""
                     + " \n"
                     + "and ThreadName = " + Thread.currentThread().getName()
                     + "\nmessage is\nGood " + good.toString() + " added");
        } catch (SQLException e) {
            log.debug(" CUSTOM-DEBUG-IN-ThreadID = \n" + Thread.currentThread().getId() + ""
                      + " \n"
                      + "and ThreadName = " + Thread.currentThread().getName()
                      + "\nmessage is\nDropped down " + this.getClass().getCanonicalName()
                      + " because of \n" + e
                              .getMessage() + " + " + getClass().getName());
        }
    }

    @Override
    synchronized public void deleteGoodByName(String name) {

        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY)) {
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            log.info(" CUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId() + ""
                     + " \n"
                     + "and ThreadName = " + Thread.currentThread().getName()
                     + "\nmessage is\nGood with " + name + " deleted + " + getClass().getName());
        } catch (SQLException e) {
            log.debug(" CUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId() + ""
                      + " \n"
                      + "and ThreadName = " + Thread.currentThread().getName()
                      + "\nmessage is\nDropped down " + this.getClass().getCanonicalName()
                      + " because of \n" + e
                              .getMessage() + " + " + getClass().getName());
        }
    }

    @Override
    synchronized public void updateGood(Good good) {

        try (Connection connection = DATA_SOURCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY)) {
            preparedStatement.setDouble(1, good.getPrice());
            preparedStatement.setInt(2, good.getAmount());
            preparedStatement.setString(3, good.getDescription());
            preparedStatement.setString(4, good.getName());
            preparedStatement.execute();
            log.info("\nCUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId() + ""
                     + " \n"
                     + "and ThreadName = " + Thread.currentThread().getName()
                     + "\nmessage is\nGood " + good.toString() + " updated + "
                     + getClass().getName());
        } catch (SQLException e) {
            log.debug("\nCUSTOM-DEBUG-IN-ThreadID = \n" + Thread.currentThread().getId() + ""
                      + " \n"
                      + "and ThreadName = " + Thread.currentThread().getName()
                      + "\nmessage is\nDropped down " + this.getClass().getCanonicalName()
                      + " because of \n" + e
                              .getMessage() + " + " + getClass().getName());
        }
    }

}
