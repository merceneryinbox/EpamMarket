package services;

import dao.CartDAO;
import dao.GoodDAO;
import dao.PostgresCartDAO;
import dao.PostgresGoodDAO;
import entities.CartCase;
import entities.Good;
import entities.Reserve;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReserveService {
    private CartDAO cartDAO;
    private GoodDAO goodDAO;

    public ReserveService() {
        this.goodDAO = new PostgresGoodDAO();
        this.cartDAO = new PostgresCartDAO();
    }

    public void reserveGoods(int userId, int goodsId, int amount) {
        Optional<Reserve> optionalReserve = cartDAO.getReserve(userId, goodsId);
        int amountForSet = amount;
        if (optionalReserve.isPresent()) {
            amountForSet += optionalReserve.get().getAmount();
        }
        cartDAO.setAmountByLoginAndGoodId(userId, goodsId, amountForSet);
    }

    public List<CartCase> getCart(int userId) {
        List<Reserve> cart;
        List<CartCase> listForCart = new ArrayList<>();
        Optional<List<Reserve>> optionalReserve = cartDAO.getReserveListByLogin(userId);
        if (optionalReserve.isPresent()) {
            cart = optionalReserve.get();
            for (Reserve reserve : cart) {
                CartCase cartCase = new CartCase();
                cartCase.setGoodName(goodDAO.getGoodById(reserve.getGoodId()).get().getName());
                cartCase.setAmount(reserve.getAmount());
                cartCase.setPrice(goodDAO.getGoodById(reserve.getGoodId()).get().getPrice());
                listForCart.add(cartCase);
            }
        }
        return listForCart;
    }
    public void deleteGoods(int userId,int goodsId){
        PostgresCartDAO postgresCartDAO = new PostgresCartDAO();
        postgresCartDAO.deleteReserve(userId,goodsId);
    }
}
