package services;

import dao.CartDAO;
import dao.GoodDAO;
import dao.PostgresCartDAO;
import dao.PostgresGoodDAO;
import entities.CartCase;
import entities.Good;
import entities.Reserve;
import lombok.extern.log4j.Log4j2;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public class ReserveService {

    //--------------------------------SINGLETON------------------------------------------

    private static ReserveService instance;
    private static ReserveService testInstance;
    private        CartDAO        cartDAO;
    private        GoodDAO        goodDAO;

    private ReserveService(CartDAO cartDAO, GoodDAO goodDAO) {
        this.cartDAO = cartDAO;
        this.goodDAO = goodDAO;
    }

    //--------------------------------------------------------------------------

    synchronized public static ReserveService getInstance() {
        if (instance == null) {
            instance = new ReserveService(PostgresCartDAO.getInstance(),
                                          PostgresGoodDAO.getInstance());
        }
        log.info("\nCUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId()
                 + "\nand ThreadName = " + Thread.currentThread().getName() + "\n "
                 + "message is\nInstance" + instance.toString() + " got.");
        return instance;
    }

    synchronized public static ReserveService getTestInstance() {
        if (testInstance == null) {
            testInstance = new ReserveService(PostgresCartDAO.getTestInstance(),
                                              PostgresGoodDAO.getTestInstance());
        }
        return testInstance;
    }

    synchronized public void reserveGoods(int userId, int goodsId, int amount) {
        Optional<Reserve> optionalReserve = cartDAO.getReserve(userId, goodsId);
        int               amountForSet    = amount;
        Optional<Good>    optionalGood    = goodDAO.getGoodById(goodsId);
        Good              good;
        if (optionalGood.isPresent()) {
            good = optionalGood.get();
            log.info("\nCUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId()
                     + "\nand ThreadName = " + Thread.currentThread().getName() + "\n "
                     + "message is\nGoog " + good.toString() + " reserved.");
            if (good.getAmount() >= amount) {
                if (optionalReserve.isPresent()) {
                    amountForSet += optionalReserve.get().getAmount();
                    log.info("\nCUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId()
                             + "\nand ThreadName = " + Thread.currentThread().getName() + "\n "
                             + "message is\nIt's amountForSet " + amountForSet + " gathered.");
                }
                good.setAmount(good.getAmount() - amount);
                goodDAO.updateGood(good);
                cartDAO.setAmountByLoginAndGoodId(userId, goodsId, amountForSet);
            }
        }
    }

    synchronized public List<CartCase> getCart(int userId) {
        List<Reserve>           cart;
        List<CartCase>          listForCart     = new ArrayList<>();
        Optional<List<Reserve>> optionalReserve = cartDAO.getReserveListByUserId(userId);
        if (optionalReserve.isPresent()) {
            log.info("\nCUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId()
                     + "\nand ThreadName = " + Thread.currentThread().getName() + "\n "
                     + "message is\noptionalReserve is Present.");
            cart = optionalReserve.get();
            log.info("\nCUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId()
                     + "\nand ThreadName = " + Thread.currentThread().getName() + "\n "
                     + "message is\nCart " + cart.toString() + " got.");
            for (Reserve reserve : cart) {
                CartCase cartCase = new CartCase();
                cartCase.setGoodId(reserve.getGoodId());
                cartCase.setGoodName(goodDAO.getGoodById(reserve.getGoodId()).get().getName());
                cartCase.setAmount(reserve.getAmount());
                cartCase.setPrice(goodDAO.getGoodById(reserve.getGoodId()).get().getPrice());
                listForCart.add(cartCase);
                log.info("\nCUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId()
                         + "\nand ThreadName = " + Thread.currentThread().getName() + "\n "
                         + "message is\ncartCase " + cartCase.toString() + " got.\nListForCart "
                         + listForCart.toString() + " got.");
            }
        }
        listForCart.sort(null);
        return listForCart;
    }

    synchronized public void deleteGoods(int userId, int goodsId) {
        Good              good;
        Optional<Good>    optionalGood    = goodDAO.getGoodById(goodsId);
        Optional<Reserve> optionalReserve = cartDAO.getReserve(userId, goodsId);
        Reserve           reserve;
        if (optionalGood.isPresent() && optionalReserve.isPresent()) {
            good = optionalGood.get();
            reserve = optionalReserve.get();
            good.setAmount(good.getAmount() + reserve.getAmount());
            goodDAO.updateGood(good);
            cartDAO.deleteReserve(userId, goodsId);
            log.info("\nCUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId()
                     + "\nand ThreadName = " + Thread.currentThread().getName() + "\n "
                     + "message is\nGood " + good.toString() + " deleted from reserve "
                     + reserve.toString());
        }
    }

    synchronized public void deleteAllOverdueReserves() {
        Optional<List<Reserve>> optionalReserve = cartDAO.getAllReserves();
        List<Reserve>           reserves;
        long                    a               = Timestamp.from(Instant.now()).getTime();
        long                    b;
        if (optionalReserve.isPresent()) {
            reserves = optionalReserve.get();
            for (Reserve reserve : reserves) {
                b = reserve.getReserveTime().getTime();
                long hour = 3600000;
                if (a - b > hour) {
                    deleteGoods(reserve.getUserId(), reserve.getGoodId());
                    log.info("\nCUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId()
                             + "\nand ThreadName = " + Thread.currentThread().getName() + "\n " +
                             "message is\nThe cart was deleted because of time expired.");
                }
            }
        }
    }

    synchronized public void deleteUserReservesAfterPayment(int userId) {
        cartDAO.deleteAllReservesByUserId(userId);
        log.info("\nCUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId()
                 + "\nand ThreadName = " + Thread.currentThread().getName() + "\n " +
                 "message is\nThe cart was deleted because of time expired  for User ID = " +
                 userId);
    }

}