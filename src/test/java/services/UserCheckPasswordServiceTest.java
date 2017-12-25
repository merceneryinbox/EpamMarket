package services;

import dao.PostgresUserDAO;
import db.DataSourceInit;
import db.DatabaseManager;
import entities.EntityGenerator;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserCheckPasswordServiceTest {

    @BeforeEach
    void init() {
        DatabaseManager.init(DataSourceInit.getH2());
    }

    @AfterEach
    void drop() {
        DatabaseManager.drop(DataSourceInit.getH2());
    }
    @Test
    void checkPassword() {
        val userDAO = PostgresUserDAO.getTestInstance();
        val userCheckPas = UserCheckPasswordService.getTestInstance();

        val user = EntityGenerator.testUserForName("Kolya");
        val user2 = EntityGenerator.testUserForName("Pete");
        userDAO.addUser(user);

        assertEquals(user,userCheckPas.checkPassword(user.getLogin(),user.getPassword()));
        assertFalse(user.equals(userCheckPas.checkPassword(user2.getLogin(),user.getPassword())));
        assertFalse(user.equals(userCheckPas.checkPassword(user2.getLogin(),"0001000")));
    }

}