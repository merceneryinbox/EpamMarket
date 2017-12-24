package services;

import dao.GoodDAO;
import dao.PostgresGoodDAO;
import entities.Good;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class GoodsService {

    //--------------------------------SINGLETON------------------------------------------

    private static GoodsService instance = null;

    synchronized public static GoodsService getInstance() {
        if (instance == null)
            instance = new GoodsService(PostgresGoodDAO.getInstance());
        return instance;
    }

    private static GoodsService testInstance;

    synchronized public static GoodsService getTestInstance() {
        if (testInstance == null)
            testInstance = new GoodsService(PostgresGoodDAO.getTestInstance());
        return testInstance;
    }

    private GoodsService(GoodDAO GoodDAO) {
        this.goodDAO = GoodDAO;
    }

    //-------------------------------------------------------------------------------

    private GoodDAO goodDAO;

    synchronized public List<Good> getPriceList() {
        List<Good> goods = goodDAO.getAllGoods();
        goods.sort(null);
        return goods;
    }
}

