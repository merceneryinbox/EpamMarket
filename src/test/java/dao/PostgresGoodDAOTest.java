package dao;

import db.DataSourceInit;
import entities.Good;
import db.DatabaseManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostgresGoodDAOTest {
    public static final DataSource DATA_SOURCE = DataSourceInit.getH2();
    public static final GoodDAO GOOD_DAO = PostgresGoodDAO.getTestInstance();

    @BeforeEach
    void init() {
        DatabaseManager.init(DATA_SOURCE);
    }

    @AfterEach
    void drop() {
        DatabaseManager.drop(DATA_SOURCE);
    }

    @Test
    public void testAddGetUpdateDeleteDao() {

        // ADD GOOD
        final String name = "Mersedes";
        Good good = Good.testGoodForName(name);
        GOOD_DAO.addGood(good);

        // GET GOOD
        Optional<Good> inserted = GOOD_DAO.getGoodByName(name);
        assertTrue(inserted.isPresent());

        // UPDATE GOOD
        final String updatedDescription = "updated description";
        good.setDescription(updatedDescription);
        GOOD_DAO.updateGood(good);
        Optional<Good> updated = GOOD_DAO.getGoodByName(name);
        assertTrue(updated.isPresent());
        assertEquals(updated.get().getDescription(), updatedDescription);

        // DELETE GOOD
        GOOD_DAO.deleteGoodByName(name);
        Optional<Good> deleted = GOOD_DAO.getGoodByName(name);
        assertFalse(deleted.isPresent());
    }
}