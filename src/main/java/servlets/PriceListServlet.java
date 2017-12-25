package servlets;

import entities.Good;
import lombok.extern.log4j.Log4j2;
import services.GoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@WebServlet(name = "PriceList", value = "/price_list")
public class PriceListServlet extends HttpServlet {
    private static GoodsService goodsService = GoodsService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        List<Good> priceList = new ArrayList<>(goodsService.getPriceList());

        try {
            req.setAttribute("priceList", priceList);
            log.info("Pricelist object " + priceList.toString() + " set into request.\nRedirection to pricelist.jsp.");
            req.getRequestDispatcher("/pricelist.jsp").forward(req, resp);
        } catch (ServletException e) {
            log.debug("Servlet dropped down because of " + e.getMessage());
        } catch (IOException e) {
            log.debug("Servlet dropped down because of " + e.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        int amount;
        try {
            amount = Integer.valueOf(req.getParameter("amount"));
            log.info("Trigger block for amount = " + amount + " check starts.\n");
            if (amount >= 0) {
                log.info("Amount = " + amount + " >=0.\nRedirect to cart.jsp.\n");
                req.getRequestDispatcher("cart").forward(req, resp);
            } else {
                log.info("Amount = " + amount + " <0.\nRedirect to priceList.jsp.\n");
                resp.sendRedirect("price_list");
            }
        } catch (ServletException e) {
            log.debug("Servlet dropped down because of " + e.getMessage());
        } catch (IOException e) {
            log.debug("Servlet dropped down because of " + e.getMessage());
        }
    }
}