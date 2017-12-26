package services;

import dao.GoodDAO;
import dao.PostgresGoodDAO;
import db.DataSourceInit;
import db.DatabaseManager;
import entities.EntityGenerator;
import entities.Good;
import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

class GoodsServiceTest {

    @BeforeEach
    void init() {
        DatabaseManager.init(DataSourceInit.getH2());
    }

    @AfterEach
    void drop() {
        DatabaseManager.drop(DataSourceInit.getH2());
    }

    @Test
    void getPriceList() {
        val goodsDAO = PostgresGoodDAO.getTestInstance();
        val product1 = EntityGenerator.testGoodForName("Dddd");
        val product2 = EntityGenerator.testGoodForName("Aaaa");
        val product3 = EntityGenerator.testGoodForName("Vvvv");
        goodsDAO.addGood(product1);
        goodsDAO.addGood(product2);
        goodsDAO.addGood(product3);
        val actualList = GoodsService.getTestInstance().getPriceList();
        val expectedList = new ArrayList<Good>();
        expectedList.add(product2);
        expectedList.add(product1);
        expectedList.add(product3);

//        assertThat(expectedList, is(actualList));

        int i=0;
        for(Good g : actualList){
            val good = (Good)expectedList.get(i);
            assertEquals(g.getName(),good.getName());
            i++;
        }


    }

}