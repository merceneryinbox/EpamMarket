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
        DataSource instance = DataSourceInit.getDataSource();
        try (Connection connection = instance.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
             ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()) {
                System.out.println("login: " + resultSet.getString("login"));
                System.out.println("email: " + resultSet.getString("email"));
                System.out.println("phone: " + resultSet.getString("phone"));
                System.out.println("password: " + resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
