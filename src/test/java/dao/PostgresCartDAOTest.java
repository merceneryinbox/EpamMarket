package dao;

import db.DataSourceInit;
import db.DatabaseManager;
import entities.Good;
import entities.Reserve;
import entities.User;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
public class PostgresCartDAOTest {
    public static final DataSource DATA_SOURCE = DataSourceInit.getH2();

    public static final CartDAO CART_DAO = PostgresCartDAO.getTestInstance();
    public static final UserDAO USER_DAO = PostgresUserDAO.getTestInstance();
    public static final GoodDAO GOOD_DAO = PostgresGoodDAO.getTestInstance();

    @BeforeEach
    public void init() {
        DatabaseManager.init(DATA_SOURCE);
    }

    @AfterEach
    public void drop() {
        DatabaseManager.drop(DATA_SOURCE);
    }

    @Test
    public void testReserveDao() {
        final String login = "Nikita";
        final String goodName = "Mersedes";
        final Integer initialAmount = 159;

        USER_DAO.createNew(User.testUserForName(login));
        val user = USER_DAO.getUserByLogin(login);
        Integer userId = user.get().getId();
        log.info("Test Log info into console ", getClass().getName());
        GOOD_DAO.addGood(Good.testGoodForName(goodName));
        val good = GOOD_DAO.getGoodByName(goodName);
        val goodId = good.get().getId();

        // ADD RESERVE
        CART_DAO.setAmountByLoginAndGoodId(userId, goodId, initialAmount);
        Optional<Reserve> inserted = CART_DAO.getReserve(userId, goodId);
        assertTrue(inserted.isPresent());

        // UPDATE RESERVE
        final Integer updatedAmount = 753;
        CART_DAO.setAmountByLoginAndGoodId(userId, goodId, updatedAmount);
        assertThat(CART_DAO.getReserve(userId, goodId).get().getAmount(), is(updatedAmount));

        // GET RESERVE
        val updated = CART_DAO.getReserve(userId, goodId);
        assertTrue(updated.isPresent());
        assertThat(updated.get().getAmount(), is(updatedAmount));

        // DELETE RESERVE
        CART_DAO.setAmountByLoginAndGoodId(userId, goodId, 0);
        val deleted = CART_DAO.getReserve(userId, goodId);
        assertFalse(deleted.isPresent());
    }
}