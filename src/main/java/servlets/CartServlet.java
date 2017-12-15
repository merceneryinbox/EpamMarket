package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "Cart", value = "/cart")
public class CartServlet extends HttpServlet {
	
	
	@Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//		String        login;
//		String        password;
		Integer       id_cart;
		Integer       id_user;
		Integer       id_good;
		Integer       amount_cart;
		LocalDateTime reserve_time;
		
		if (req != null) {
//			login = (String) req.getAttribute("login");
			id_user = (Integer) req.getAttribute("id_user");
//			password = (String) req.getAttribute("password");
			id_cart = (Integer) req.getAttribute("id_cart");
			id_good = (Integer) req.getAttribute("id_good");
			amount_cart = (Integer) req.getAttribute("amount_cart");
			reserve_time = (LocalDateTime) req.getAttribute("reserve_time");
			
			HttpSession cartSession = req.getSession();
			
			if (cartSession != null) {
				cartSession.setAttribute("id_cart", id_cart);
				cartSession.setAttribute("id_good", id_good);
				cartSession.setAttribute("id_user", id_user);
				cartSession.setAttribute("amount_cart", amount_cart);
				cartSession.setAttribute("reserve_time", reserve_time);
			}
			
			try {
				req.getRequestDispatcher("cart.jsp")
						.forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}
