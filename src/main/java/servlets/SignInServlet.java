package servlets;

import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SignIn", value = "/signin")
public class SignInServlet extends HttpServlet {
	
	private String checker;
	
	@Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
//		req.getRequestDispatcher("/signin")
//				.forward(req, resp);
		HttpSession session = req.getSession();
		String      login;
		String      password;
		if (session != null) {
			login = (String) session.getAttribute("login");
			password = (String) session.getAttribute("password");
			checker = checkUser(login, password);
			
			if (checker.equalsIgnoreCase("ban")) {
				req.getRequestDispatcher("/banneduser.jsp")
						.forward(req, resp);
			}
		}
	}
	
	private String checkUser(String login, String password) {
		String      result      = "not banned";
		UserService userService = new UserService(login,password);
		result = userService.checkBanInBase();
		return result;
	}
}
