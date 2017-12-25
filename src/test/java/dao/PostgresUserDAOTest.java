package dao;

import db.DataSourceInit;
import db.DatabaseManager;
import entities.EntityGenerator;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PostgresUserDAOTest {

    public static final DataSource DATA_SOURCE = DataSourceInit.getH2();

    public static final UserDAO USER_DAO = PostgresUserDAO.getTestInstance();

    @BeforeEach
    public void init() {
        DatabaseManager.init(DATA_SOURCE);
    }

    @AfterEach
    public void drop() {
        DatabaseManager.drop(DATA_SOURCE);
    }

    @Test
    public void testAddGetUpdateDeleteDao() {
        val user = EntityGenerator.testUserForName("Vasya");

        // ADD USER
        USER_DAO.addUser(user);
        val added = USER_DAO.getUserByLogin("Vasya");
        assertTrue(added.isPresent());

        // DELETE USER
        USER_DAO.deleteUserByLogin("Vasya");
        val deleted = USER_DAO.getUserByLogin("Vasya");
        assertFalse(deleted.isPresent());

    }

    @Test
    public void testUpdating() {
        // Creating user
        val userInitial = EntityGenerator.testUserForName("Vasya");
        USER_DAO.addUser(userInitial);

        // Updating password
        val userWithNewPassword = EntityGenerator.clone(userInitial).setPassword("new password");
        USER_DAO.updateUser(userWithNewPassword, null);

        //Checking if password updated
        val extractedUserWithNewPassword = USER_DAO.getUserByLogin("Vasya").get();
        assertThat(extractedUserWithNewPassword.getPassword(), is("new password"));
    }

    @Test
    public void testByIdOperations(){
        val user = EntityGenerator.testUserForName("Vasya");

        // ADD USER
        USER_DAO.addUser(user);
        val gotByLogin = USER_DAO.getUserByLogin("Vasya");
        assertTrue(gotByLogin.isPresent());

        val id = gotByLogin.get().getId();

        // GET BY ID
        val gotById = USER_DAO.getUserById(id);

        assertThat(user, is(gotById.get()));
        assertThat(gotByLogin, is(gotById));

        // DELETE USER
        USER_DAO.deleteUserById(id);
        val deleted = USER_DAO.getUserByLogin("Vasya");
        assertFalse(deleted.isPresent());

    }
}
