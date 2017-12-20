package dao;

import DbConnection.DataSourceInit;
import entities.Good;
import DbConnection.DatabaseManager;
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
    public static final GoodDAO GOOD_DAO = new PostgresGoodDAO(DATA_SOURCE);

    @BeforeEach
    void prepare() {
        DatabaseManager.init(DATA_SOURCE);
    }

    @AfterEach
    void finish() {
        DatabaseManager.drop(DATA_SOURCE);

    }

    @Test
    public void testGoodDao() {
        final String name = "Mersedes";
        final String updatedDescription = "updated description";
        Good good = Good.testGoodForName(name);

        System.out.println("Trying to insert : " + good);
        GOOD_DAO.addGood(good);
        Optional<Good> inserted = GOOD_DAO.getGoodByName(name);
        System.out.println(inserted);
        assertTrue(inserted.isPresent());
        System.out.println("Extracted : " + inserted);

        // UPDATE GOOD
        good.setDescription(updatedDescription);
        System.out.println("Trying to update : " + good);
        GOOD_DAO.updateGood(good);
        Optional<Good> updated = GOOD_DAO.getGoodByName(name);
        assertTrue(updated.isPresent());
        assertEquals(updated.get().getDescription(), updatedDescription);
        System.out.println("Extracted : " + updated);

        // DELETE GOOD
        GOOD_DAO.deleteGoodByName(name);
        Optional<Good> deleted = GOOD_DAO.getGoodByName(name);
        assertFalse(deleted.isPresent());
        System.out.println("Extracted : " + deleted);
    }
}