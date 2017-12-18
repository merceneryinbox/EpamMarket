package servlets;

import entities.User;
import services.UserRegistrator;
import services.UserStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/sign_up")
public class RegistrationServlet extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		
		if (req != null) {
			String login = (String) req.getAttribute("login");
			String password = (String) req.getAttribute("password");
			
			if (login != null && password != null) {
				
				HttpSession cartSession = req.getSession();
				if (cartSession != null) {
					User user = new User();
					user.setLogin(login);
					user.setPassword(password);
					user.setStatus(UserStatus.ACTIVE.name());
					
					cartSession.setAttribute("user", user);
					UserRegistrator userRegistrator = new UserRegistrator();
					userRegistrator.registrate(user);
					try {
						req.getRequestDispatcher("/signin.jsp").forward(req, resp);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						req.getRequestDispatcher("/signup.jsp").forward(req, resp);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
