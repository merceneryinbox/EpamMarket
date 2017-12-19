package servlets;

import dao.PostgresUserDAO;
import entities.User;
import services.UserStatus;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Registrarion", value = "/sign_up")
public class SignUpServlet extends HttpServlet {
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		String login;
		String password;
		String email;
		String phone;
		String statusDefault;
		User user = new User();
		PostgresUserDAO postgresUserDAO = new PostgresUserDAO();
		
		if (req != null) {
			login = (String) req.getAttribute("login");
			password = (String) req.getAttribute("password");
			email = (String) req.getAttribute("email");
			phone = (String) req.getAttribute("phone");
			statusDefault = UserStatus.ACTIVE.name();
			
			user.setLogin(login);
			user.setPassword(password);
			user.setEmail(email);
			user.setPhone(phone);
			user.setStatus(statusDefault);
			
			try {
				HttpSession registrationSession = req.getSession();
				registrationSession.setAttribute("user", user);
				postgresUserDAO.createNew(user);
				
				req.getRequestDispatcher("pricelist.jsp").forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}