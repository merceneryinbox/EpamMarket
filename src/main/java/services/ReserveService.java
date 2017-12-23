package services;

import dao.CartDAO;
import dao.PostgresCartDAO;
import entities.Reserve;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReserveService {
    private CartDAO cartDAO;

    public ReserveService() {
        this.cartDAO = new PostgresCartDAO();
    }

    public void reserveGoods(int userId, int goodsId, int amount) {
        Optional<Reserve> optionalReserve = cartDAO.getReserve(userId, goodsId);
        int amountForSet = amount;
        if (optionalReserve.isPresent()) {
            amountForSet+= optionalReserve.get().getAmount();
        }
        cartDAO.setAmountByLoginAndGoodId(userId,goodsId,amountForSet);
    }

    public List<Reserve> getCart(int userId) {
        List<Reserve> cart = new ArrayList<>();
        Optional<List<Reserve>> optionalReserve = cartDAO.getReserveListByLogin(userId);
        if(optionalReserve.isPresent()) {
            cart = optionalReserve.get();
        }
        return cart;
    }
    public void deleteGoods(int userId,int goodsId){
        PostgresCartDAO postgresCartDAO = new PostgresCartDAO();
        postgresCartDAO.deleteReserve(userId,goodsId);
    }
}
