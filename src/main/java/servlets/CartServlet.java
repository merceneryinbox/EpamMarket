package servlets;

import dao.PostgresCartDAO;
import dao.PostgresUserDAO;
import entities.Reserve;
import entities.User;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Log4j2()
@WebServlet(name = "Cart", value = "/cart")
public class CartServlet extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
			ServletException, IOException {
		Reserve reserve = new Reserve();
		User user = new User();
		PostgresCartDAO postgresCartDAO = new PostgresCartDAO();
		PostgresUserDAO postgresUserDAO = new PostgresUserDAO();
		Integer userId;
		String login;
		Integer goodsamount;
		Integer goodsId;
		
		HttpSession cartSession = request.getSession();
		
		login = (String) cartSession.getAttribute("login    ");
		Optional<User> optionalUser = postgresUserDAO.getUserByLogin(login);
		
		if (optionalUser.isPresent()) {
			user = optionalUser.get();
			userId = user.getId();
			
			goodsamount = (Integer) request.getAttribute("amount");
			goodsId = (Integer) request.getAttribute("goods_id");
			
			reserve.setUserId(userId);
			reserve.setGoodId(goodsId);
			reserve.setAmount(goodsamount);
			
			request.setAttribute("cart", reserve);
		} else {
			request.getRequestDispatcher("/signup.jsp").forward(request, response);
		}
	}
}
