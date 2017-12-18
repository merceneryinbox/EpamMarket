package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "StartPage", value = "/")
public class StartPageServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		
		HttpSession session = req.getSession();
		
		if (session != null) {
			String login = (String) session.getAttribute("login");
			String password = (String) session.getAttribute("password");
			if (login != null && password != null) {
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			} else {
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			}
		}
	}
}
