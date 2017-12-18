package temp;

import dao.UserDAO;
import dbConnection.DataSourceInit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;

public class Market {

    private static final Logger logger = LogManager.getLogger(Market.class);

    public static void main(String[] args) throws PropertyVetoException, SQLException, IOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            DataSource instance = DataSourceInit.getDataSource();
            connection = instance.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM users");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("login: " + resultSet.getString("login"));
                System.out.println("email: " + resultSet.getString("email"));
                System.out.println("phone_number: " + resultSet.getString("phone"));
                System.out.println("password: " + resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (preparedStatement != null) try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(MarketConfiguration.class);
        UserDAO userDAO = context.getBean(UserDAO.class);
        logger.info(userDAO.getUserByLogin(""));
        context.registerShutdownHook();
    }
}
