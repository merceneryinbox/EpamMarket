package temp;

import DbConnection.DataSourceInit;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Market {
    public static void main(String[] args) throws PropertyVetoException, SQLException, IOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            DataSource instance = DataSourceInit.getDataSource();
            connection = instance.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO users (login, email, phone, password) VALUES (?, ?, ?, ?)");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("login: " + resultSet.getString("login"));
                System.out.println("email: " + resultSet.getString("email"));
                System.out.println("phone: " + resultSet.getString("phone"));
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
    }
}
