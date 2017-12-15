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
	String userName = null;
	String userPass = null;
	
	@Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.getRequestDispatcher("sign_up.jsp")
				.forward(req, resp);
		if (req != null) {
			
			userName = (String) req.getAttribute("name");
			userPass = (String) req.getAttribute("password");
			HttpSession cartSession = req.getSession();
			if (cartSession == null) {
				cartSession.setAttribute("name", userName);
				cartSession.setAttribute("password", userPass);
			}
		}
	}
}
