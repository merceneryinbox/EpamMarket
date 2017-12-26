package services;

import dao.PostgresUserDAO;
import db.DataSourceInit;
import db.DatabaseManager;
import entities.EntityGenerator;
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
        val user = EntityGenerator.testUserForName("Vasya");
        val user2 = EntityGenerator.testUserForName("Pete");
        userDAO.addUser(user);
        userDAO.addUser(user2);
        AdminService adminService = AdminService.getTestInstance();
        val list = adminService.getUserList();

        assertTrue(list.contains(user));
        assertTrue(list.contains(user2));
        assertFalse(list.contains(EntityGenerator.testUserForName("Lilya")));

    }

}