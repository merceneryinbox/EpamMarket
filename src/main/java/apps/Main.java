package apps;

import dao.PostgresCartDAO;
import dao.PostgresGoodDAO;
import entities.Good;
import lombok.val;

public class Main {
    public static void main(String[] args) {
        //testGoodDao();
        testReserveDao();
    }

    public static void testGoodDao() {
        val goodDAO = new PostgresGoodDAO();
        val name = "Ololoshka";
        val good = Good.testGoodForName(name);

        // ADD GOOD
        System.out.println("Trying to insert : " + good);
        goodDAO.addGood(good);

        // GET GOOD
        val extracted = goodDAO.getGoodByName(name);
        System.out.println("Extracted : " + extracted);

        // UPDATE GOOD
        good.setDescription("Updated description");
        System.out.println("Trying to update : " + good);
        goodDAO.updateGood(good);

        // GET GOOD
        val extracted2 = goodDAO.getGoodByName(name);
        System.out.println("Extracted : " + extracted2);

        // DELETE GOOD
        goodDAO.deleteGoodByName(name);

        // GET GOOD
        val extracted3 = goodDAO.getGoodByName(name);
        System.out.println("Extracted : " + extracted3);
    }

    public static void testReserveDao() {
        val cartDao = new PostgresCartDAO();
        val login = "Ololoshka";
        val goodId = 1;

        // ADD RESERVE
        System.out.println("Trying to insert : " + 159);
        cartDao.setAmountByLoginAndGoodId(login, goodId, 159);

        // GET RESERVE
        val extracted = cartDao.getReserve(login, goodId);
        System.out.println("Extracted : " + extracted);

        // UPDATE RESERVE
        System.out.println("Trying to update : " + 753);
        cartDao.setAmountByLoginAndGoodId(login, goodId, 753);

        // GET RESERVE LIST
        val extracted1 = cartDao.getReserveListByLogin(login);
        System.out.println("Extracted list : " + extracted1);

        // DELETE RESERVE
        cartDao.setAmountByLoginAndGoodId(login, goodId, 0);

        // GET RESERVE
        val extracted2 = cartDao.getReserve(login, goodId);
        System.out.println("Extracted : " + extracted2);
    }
}
