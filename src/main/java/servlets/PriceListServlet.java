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
            if (amount >= 0) {
                req.getRequestDispatcher("cart").forward(req, resp);
            } else {
                resp.sendRedirect("price_list");
            }
        } catch (ServletException e) {
            log.debug("Servlet dropped down because of " + e.getMessage());
        } catch (IOException e) {
            log.debug("Servlet dropped down because of " + e.getMessage());
        }
    }
}