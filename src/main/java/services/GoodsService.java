package services;

import dao.GoodDAO;
import dao.PostgresGoodDAO;
import entities.Good;

import java.util.List;

public class GoodsService {
    private GoodDAO goodDAO;

    public GoodsService() {
        goodDAO = new PostgresGoodDAO();
    }

    public List<Good> getPriceList() {
        return goodDAO.getAllGoods();
    }
}
