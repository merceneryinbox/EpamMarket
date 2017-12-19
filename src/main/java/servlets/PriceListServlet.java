package servlets;

import entities.Good;
import services.AllGoodsGetterService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "PriceList", value = "/price_list")
public class PriceListServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		AllGoodsGetterService allGoodsGetter = new AllGoodsGetterService();
		Map<String, List<Good>> priceList = new HashMap<>();
		priceList.put("priceList", allGoodsGetter.getPriceList());
		HttpSession session = req.getSession();
		session.setAttribute("priceList", priceList);
		
		req.getRequestDispatcher("/pricelist.jsp").forward(req, resp);
	}
}