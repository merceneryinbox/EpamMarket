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
				
				// Redirect to authorized pricelist page from last not expired session
				req.getRequestDispatcher("pricelist.jsp").forward(req, resp);
			} else {
				
				// else redirect to start page
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			}
		}
	}
}
