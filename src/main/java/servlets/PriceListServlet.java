package servlets;

import entities.Good;
import services.GoodsService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
}