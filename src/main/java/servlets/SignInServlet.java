package servlets;

import org.springframework.stereotype.Service;
import services.BanUserCheckService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service
@WebServlet(name = "SignIn", value = "/signin")
public class SignInServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		
		HttpSession session = req.getSession();
		String login;
		
		if (session != null) {
			try {
				login = (String) session.getAttribute("login");
				if (checkUserBan(login)) {
					
					// if user is banned => redirect him to ban page
					req.getRequestDispatcher("/banneduser.jsp").forward(req, resp);
				} else {
					// else to signin.jsp page
					req.getRequestDispatcher("signin.jsp").forward(req, resp);
				}
				// if session not null but loin is null then go to start page
			} catch (Exception ignore) {
				req.getRequestDispatcher("index.jsp").forward(req, resp);
			}
		}
		
	}
	
	/**
	 * Checking if he banned earlyer
	 * @param login
	 * @return
	 */
	private boolean checkUserBan(String login) {
		boolean result;
		BanUserCheckService banUserCheckService = new BanUserCheckService(login);
		result = banUserCheckService.checkUserBan();
		return result;
	}
}
