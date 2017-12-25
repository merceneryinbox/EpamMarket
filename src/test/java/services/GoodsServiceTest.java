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
        val testFromGetPriceList = GoodsService.getTestInstance().getPriceList();
        val testList = new ArrayList();
        testList.add(product2);
        testList.add(product1);
        testList.add(product3);

        int i=0;
        for(Good g : testFromGetPriceList){
            val good = (Good)testList.get(i);
            assertEquals(g.getName(),good.getName());
            i++;
        }


    }

}