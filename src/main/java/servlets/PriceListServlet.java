package servlets;

import entities.Good;
import services.AllGoodsGetterService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PriceList", value = "/price_list")
public class PriceListServlet extends HttpServlet {
	private final static List<Good> priceList = new ArrayList<>();
	private static AllGoodsGetterService allGoodsGetter = new AllGoodsGetterService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		priceList.addAll(allGoodsGetter.getPriceList());
		try {
			req.setAttribute("priceList", priceList);
			req.getRequestDispatcher("/pricelist.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}