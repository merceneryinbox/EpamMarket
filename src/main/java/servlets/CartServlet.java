package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	String login    = null;
	String password = null;
	long   cartId   = 0;
	
	@Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (req != null) {
			login = (String) req.getAttribute("name");
			password = (String) req.getAttribute("password");
			cartId = (Long) req.getAttribute("id_cart");
			HttpSession cartSession = req.getSession();
			if (login != null && password != null && cartId != 0) {
				cartSession.setAttribute("name", login);
				cartSession.setAttribute("password", password);
				
			}
			req.getRequestDispatcher("cart.jsp")
					.forward(req, resp);
		}
	}
	
	@Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("cart.jsp")
				.forward(req, resp);
	}
	
	@Override protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("cart.jsp")
				.forward(req, resp);
	}
	
	@Override protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("cart.jsp")
				.forward(req, resp);
	}
}
