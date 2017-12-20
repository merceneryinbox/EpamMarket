package services;

import dao.PostgresGoodDAO;
import entities.Good;

import java.util.List;

public class AllGoodsGetterService {
	public List<Good> getPriceList() {
		return new PostgresGoodDAO().getAllGoods();
	}
}
