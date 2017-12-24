package services;

import dao.CartDAO;
import dao.GoodDAO;
import dao.PostgresCartDAO;
import dao.PostgresGoodDAO;
import entities.CartCase;
import entities.Reserve;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReserveService {

    //--------------------------------SINGLETON------------------------------------------

    private static ReserveService instance;

    synchronized public static ReserveService getInstance() {
        if (instance == null)
            instance = new ReserveService(PostgresCartDAO.getInstance(), PostgresGoodDAO.getInstance());
        return instance;
    }

    private static ReserveService testInstance;

    synchronized public static ReserveService getTestInstance() {
        if (testInstance == null)
            testInstance = new ReserveService(PostgresCartDAO.getTestInstance(), PostgresGoodDAO.getTestInstance());
        return testInstance;
    }

    private ReserveService(CartDAO cartDAO, GoodDAO goodDAO) {
        this.cartDAO = cartDAO;
        this.goodDAO = goodDAO;
    }

    //--------------------------------------------------------------------------

    private CartDAO cartDAO;
    private GoodDAO goodDAO;

    synchronized public void reserveGoods(int userId, int goodsId, int amount) {
        Optional<Reserve> optionalReserve = cartDAO.getReserve(userId, goodsId);
        int amountForSet = amount;
        if (optionalReserve.isPresent()) {
            amountForSet += optionalReserve.get().getAmount();
        }
        cartDAO.setAmountByLoginAndGoodId(userId, goodsId, amountForSet);
    }

    synchronized public List<CartCase> getCart(int userId) {
        List<Reserve> cart;
        List<CartCase> listForCart = new ArrayList<>();
        Optional<List<Reserve>> optionalReserve = cartDAO.getReserveListByLogin(userId);
        if (optionalReserve.isPresent()) {
            cart = optionalReserve.get();
            for (Reserve reserve : cart) {
                CartCase cartCase = new CartCase();
                cartCase.setGoodId(reserve.getGoodId());
                cartCase.setGoodName(goodDAO.getGoodById(reserve.getGoodId()).get().getName());
                cartCase.setAmount(reserve.getAmount());
                cartCase.setPrice(goodDAO.getGoodById(reserve.getGoodId()).get().getPrice());
                listForCart.add(cartCase);
            }
        }
        return listForCart;
    }

    synchronized public void deleteGoods(int userId, int goodsId) {
        cartDAO.deleteReserve(userId, goodsId);
    }
}
