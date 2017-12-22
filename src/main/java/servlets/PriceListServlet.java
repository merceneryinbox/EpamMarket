package servlets;

import entities.Good;
import entities.User;
import services.GoodsService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PriceList", value = "/price_list")
public class PriceListServlet extends HttpServlet {
	private static GoodsService goodsService = new GoodsService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		List<Good> priceList = new ArrayList<>(goodsService.getPriceList());

		try {
			req.setAttribute("priceList", priceList);
			req.getRequestDispatcher("/pricelist.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		int amount;

			try {
			amount =Integer.valueOf(req.getParameter("amount"));
			if(amount>=0){
                req.getRequestDispatcher("cart").forward(req, resp);
			}
			else{
				resp.sendRedirect("price_list");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}