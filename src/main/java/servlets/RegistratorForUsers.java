package servlets;

import entities.User;
import org.springframework.stereotype.Service;
import services.UserRole;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service
@WebServlet(name = "Registrator", value = "/registrator")
public class RegistratorForUsers extends HttpServlet {
	private static User user;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		HttpSession session = req.getSession();
		if (session != null) {
			user.setLogin((String) session.getAttribute("login"));
			user.setPassword((String) session.getAttribute("password"));
			user.setPhone((String) session.getAttribute("phone"));
			user.setEmail((String) session.getAttribute("email"));
			
			// TODO: 18.12.2017 two forwad mathods need to be refactored in dao & db
			user.setStatus(false);
			user.setRole(UserRole.User);
		}
	}
}
