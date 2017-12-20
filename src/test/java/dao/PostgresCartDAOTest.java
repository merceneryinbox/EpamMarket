package dao;

import DbConnection.DataSourceInit;
import DbConnection.DatabaseManager;
import entities.Good;
import entities.Reserve;
import entities.User;
import lombok.val;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostgresCartDAOTest {
    public static final DataSource DATA_SOURCE = DataSourceInit.getH2();
    public static final CartDAO CART_DAO = new PostgresCartDAO(DATA_SOURCE);
    public static final UserDAO USER_DAO = new PostgresUserDAO(DATA_SOURCE);
    public static final GoodDAO GOOD_DAO = new PostgresGoodDAO(DATA_SOURCE);

    @Test
    public void testReserveDao() {
        DatabaseManager.init(DATA_SOURCE);

        final String login = "Alistar";
        final String goodName = "Targon";
        final Integer initialAmount = 159;
        final Integer updatedAmount = 753;


        USER_DAO.createNew(User.testUserForName(login));
        Optional<User> user = USER_DAO.getUserByLogin(login);
        Integer userId = user.get().getId();
        System.out.println("User Id : " + userId);

        GOOD_DAO.addGood(Good.testGoodForName(goodName));
        Optional<Good> good = GOOD_DAO.getGoodByName(goodName);
        val goodId = good.get().getId();
        System.out.println("Good Id : " + goodId);

        // ADD RESERVE
        System.out.println("Trying to insert : " + initialAmount);
        CART_DAO.setAmountByLoginAndGoodId(userId, goodId, initialAmount);
        Optional<Reserve> inserted = CART_DAO.getReserve(userId, goodId);
        assertTrue(inserted.isPresent());
        System.out.println("Extracted : " + inserted);

        // UPDATE RESERVE
        System.out.println("Trying to update : " + updatedAmount);
        CART_DAO.setAmountByLoginAndGoodId(userId, goodId, updatedAmount);

        // GET RESERVE
        val updated = CART_DAO.getReserve(userId, goodId);
        assertTrue(updated.isPresent());
        assertThat(updated.get().getAmount(), is(updatedAmount));
        System.out.println("Extracted: " + updated);

        // DELETE RESERVE
        CART_DAO.setAmountByLoginAndGoodId(userId, goodId, 0);
        val deleted = CART_DAO.getReserve(userId, goodId);
        assertFalse(deleted.isPresent());
        System.out.println("Extracted : " + deleted);

        USER_DAO.deleteUserByLogin(login);
        GOOD_DAO.deleteGoodByName(goodName);

        DatabaseManager.drop(DATA_SOURCE);
    }
}