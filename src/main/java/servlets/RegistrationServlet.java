package servlets;

import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service
@WebServlet(name = "Registrarion", value = "/signup")
public class RegistrationServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		
		if (req != null) {
			String login = (String) req.getAttribute("login");
			String password = (String) req.getAttribute("password");
			Integer idUser = (Integer) req.getAttribute("id_user");
			String email = (String) req.getAttribute("email");
			String phone = (String) req.getAttribute("phone");
			String status = (String) req.getAttribute("status");
			
			HttpSession registrationSession = req.getSession();
			
			if (registrationSession == null) {
				try {
					registrationSession.setAttribute("login", login);
					registrationSession.setAttribute("password", password);
					registrationSession.setAttribute("id_user", idUser);
					registrationSession.setAttribute("email", email);
					registrationSession.setAttribute("phone", phone);
					registrationSession.setAttribute("status", status);
				} catch (NullPointerException npe) {
					try {
						req.getRequestDispatcher("/signup.jsp").forward(req, resp);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			} else {
				
				try {
					req.getRequestDispatcher("signin.jsp").forward(req, resp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}