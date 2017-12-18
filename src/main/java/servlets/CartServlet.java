package servlets;

import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
@WebServlet(name = "Cart", value = "/cart")
public class CartServlet extends HttpServlet {
	private static HttpServletRequest request;
	private static HttpServletResponse response;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		this.request = req;
		this.response = resp;
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		//		String        login;
//		String        password;
		Integer idCart;
		Integer idUser;
		Integer idGood;
		Integer amountCart;
		LocalDateTime reserveTime;
		
		if (req != null) {
//			login = (String) req.getAttribute("login");
			idUser = (Integer) req.getAttribute("id_user");
//			password = (String) req.getAttribute("password");
			idCart = (Integer) req.getAttribute("id_cart");
			idGood = (Integer) req.getAttribute("id_good");
			amountCart = (Integer) req.getAttribute("amount_cart");
			reserveTime = (LocalDateTime) req.getAttribute("reserve_time");
			
			HttpSession cartSession = req.getSession();
			
			if (cartSession != null) {
				cartSession.setAttribute("id_cart", idCart);
				cartSession.setAttribute("id_good", idGood);
				cartSession.setAttribute("id_user", idUser);
				cartSession.setAttribute("amount_cart", amountCart);
				cartSession.setAttribute("reserve_time", reserveTime);
			}
			
			try {
				req.getRequestDispatcher("cart.jsp").forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}
