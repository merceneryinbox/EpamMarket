package apps;

import dao.PostgresGoodDAO;
import entities.Good;
import lombok.val;

public class Main {
    public static void main(String[] args) {
        testGoodDao();
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


}
