package dao;

import DbConnection.DataSourceInit;
import entities.User;

public class TestingClass {
    public static void main(String[] args) {
        PostgresUserDAO postgresUserDAO = new PostgresUserDAO(DataSourceInit.getDataSource());
        User user  = new User();
        user.setLogin("vasyan007");
        user.setEmail("vasyan@mail.com");
        user.setPassword("asdasdasd");
        user.setStatus("none");
        user.setPhone("88005553535");

        System.out.println(postgresUserDAO.createNew(user));

        User user1  = new User();
        user1.setLogin("vasyan0007");
        user1.setEmail("vasyan213@mail.com");
        user1.setPassword("123asdasdasd");
        user1.setStatus("none");
        user1.setPhone("88005553535");
        postgresUserDAO.updateUser(user1,postgresUserDAO.getUserByLogin("vasyan007").get());
        System.out.println(postgresUserDAO.getUserByLogin("vasyan0007").get());
        System.out.println(postgresUserDAO.deleteUserByLogin("vasyan007"));
        System.out.println(postgresUserDAO.deleteUserByLogin("vasyan0007"));
    }
}
