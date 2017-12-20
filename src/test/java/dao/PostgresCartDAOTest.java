package dao;

import DbConnection.DataSourceInit;
import DbConnection.DatabaseManager;
import entities.Good;
import entities.Reserve;
import entities.User;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostgresCartDAOTest {

    @BeforeEach
    public void init(){

    }

    @Test
    public void testReserveDao() {
        DatabaseManager.init();

        final String login = "Alistar";
        final String goodName = "Targon";
        final Integer initialAmount = 159;
        final Integer updatedAmount = 753;

        CartDAO cartDao = new PostgresCartDAO();
        UserDAO userDao = new PostgresUserDAO(DataSourceInit.getDataSource());
        GoodDAO goodDao = new PostgresGoodDAO();

        userDao.createNew(User.testUserForName(login));
        Optional<User> user = userDao.getUserByLogin(login);
        Integer userId = user.get().getId();
        System.out.println("User Id : " + userId);

        goodDao.addGood(Good.testGoodForName(goodName));
        Optional<Good> good = goodDao.getGoodByName(goodName);
        val goodId = good.get().getId();
        System.out.println("Good Id : " + goodId);

        // ADD RESERVE
        System.out.println("Trying to insert : " + initialAmount);
        cartDao.setAmountByLoginAndGoodId(userId, goodId, initialAmount);
        Optional<Reserve> inserted = cartDao.getReserve(userId, goodId);
        assertTrue(inserted.isPresent());
        System.out.println("Extracted : " + inserted);

        // UPDATE RESERVE
        System.out.println("Trying to update : " + updatedAmount);
        cartDao.setAmountByLoginAndGoodId(userId, goodId, updatedAmount);

        // GET RESERVE
        val updated = cartDao.getReserve(userId, goodId);
        assertTrue(updated.isPresent());
        assertThat(updated.get().getAmount(),is (updatedAmount));
        System.out.println("Extracted: " + updated);

        // DELETE RESERVE
        cartDao.setAmountByLoginAndGoodId(userId, goodId, 0);
        val deleted = cartDao.getReserve(userId, goodId);
        assertFalse(deleted.isPresent());
        System.out.println("Extracted : " + deleted);

        userDao.deleteUserByLogin(login);
        goodDao.deleteGoodByName(goodName);

        DatabaseManager.drop();
    }
}