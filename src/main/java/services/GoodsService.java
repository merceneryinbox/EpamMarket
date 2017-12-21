package services;

import dao.GoodDAO;
import dao.PostgresGoodDAO;
import entities.Good;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class GoodsService {
	private GoodDAO goodDAO;
	
	public GoodsService() {
		goodDAO = new PostgresGoodDAO();
	}
	
	public List<Good> getPriceList() {
		return goodDAO.getAllGoods();
	}
}
