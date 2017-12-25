package services;

import dao.PostgresUserDAO;
import db.DataSourceInit;
import db.DatabaseManager;
import entities.EntityGenerator;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChangeStatusServiceTest {
    @BeforeEach
    void init() {
        DatabaseManager.init(DataSourceInit.getH2());
    }

    @AfterEach
    void drop() {
        DatabaseManager.drop(DataSourceInit.getH2());
    }

    @Test
    void changeStatusByIdTest() {
        val userDAO = PostgresUserDAO.getTestInstance();
        val user = EntityGenerator.testUserForName("Pete");
        userDAO.addUser(user);
        val cSS = ChangeStatusService.getTestInstance();
        cSS.changeStatusById(userDAO.getUserByLogin("Pete").get().getId());
        assertTrue(userDAO.getUserByLogin("Pete").get().getStatus().equals("BANNED"));
        cSS.changeStatusById(userDAO.getUserByLogin("Pete").get().getId());
        assertFalse(userDAO.getUserByLogin("Pete").get().getStatus().equals("BANNED"));
    }

}