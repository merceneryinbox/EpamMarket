package services;

import dao.PostgresUserDAO;
import db.DataSourceInit;
import db.DatabaseManager;
import entities.User;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminServiceTest {
    @BeforeEach
    void init(){
        DatabaseManager.init(DataSourceInit.getH2());
    }

    @AfterEach
    void drop(){
        DatabaseManager.drop(DataSourceInit.getH2());
    }

    @Test
    void getUserList() {
        val userDAO = PostgresUserDAO.getTestInstance();
        val user = User.testUserForName("Vasya");
        val user2 = User.testUserForName("Pete");
        userDAO.createNew(user);
        userDAO.createNew(user2);
        AdminService adminService = AdminService.getTestInstance();
        val list = adminService.getUserList();

        assertTrue(list.contains(user));
        assertTrue(list.contains(user2));
        assertFalse(list.contains(User.testUserForName("Lilya")));

    }

}