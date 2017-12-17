package temp.shefer;

import DbConnection.DataSourceInit;
import entities.User;
import lombok.val;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTrial {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            DataSource instance = DataSourceInit.getDataSource();
            connection = instance.getConnection();
            preparedStatement = connection.prepareStatement("SELECT id_user, login, email, phone, password, status FROM users");
            resultSet = preparedStatement.executeQuery();
            System.out.println("Hello");
            while (resultSet.next()) {
                val user = new User();
                user.setId(resultSet.getInt("id_user"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setStatus(resultSet.getString("status"));

                System.out.println(user);
            }
            System.out.println("Users!");


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
