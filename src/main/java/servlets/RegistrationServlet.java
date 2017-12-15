package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Registrarion", value = "/sign_up")
public class RegistrationServlet extends HttpServlet {
	
	
	@Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Integer id_user;
		String  login;
		String  password;
		String  email;
		String  phone;
		String  status;
		if (req != null) {
			login = (String) req.getAttribute("login");
			password = (String) req.getAttribute("password");
			id_user = (Integer) req.getAttribute("id_user");
			email = (String) req.getAttribute("email");
			phone = (String) req.getAttribute("phone");
			status = (String) req.getAttribute("status");
			
			HttpSession registrationSession = req.getSession();
			
			if (registrationSession == null) {
				registrationSession.setAttribute("login", login);
				registrationSession.setAttribute("password", password);
				registrationSession.setAttribute("id_user", id_user);
				registrationSession.setAttribute("email", email);
				registrationSession.setAttribute("phone", phone);
				registrationSession.setAttribute("status", status);
			}
			
			try {
				req.getRequestDispatcher("signup.jsp")
						.forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}