package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/sign_up")
public class RegistrationServlet extends HttpServlet {
	
	
	@Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String userName;
		String userPass;
		if (req != null) {
			userName = (String) req.getAttribute("name");
			userPass = (String) req.getAttribute("password");
			HttpSession registrationSession = req.getSession();
			if (userName != null && userPass != null) {
				registrationSession.setAttribute("name", userName);
				registrationSession.setAttribute("password", userPass);
			} else {
				
				try {
					req.getRequestDispatcher("sign_up.jsp")
							.forward(req, resp);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}
	}
}