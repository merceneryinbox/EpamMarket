package servlets;

import services.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "SignIn", value = "/sign_in")
public class SignInServlet extends HttpServlet {

	private String checker;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		String login;
		String password;
		login = (String) session.getAttribute("login");
		password = (String) session.getAttribute("password");
		if (login != null && password != null) {
			if (login.equalsIgnoreCase("admin")) {
				req.getRequestDispatcher("/adminpage.jsp").forward(req, resp);
			} else {
				req.getRequestDispatcher("/pricelist.jsp").forward(req, resp);
			}
		} else {
			req.getRequestDispatcher("/signin.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		String login;
		String password;
		login = (String) req.getParameter("login");
		password = (String) req.getParameter("password");
		//TODO: logic of verification login and password in DB
		//TODO: logic of checking login's status
		checker = "active";
		if (login.equals("admin")) {
			checker = "admin";
		}
		if (login.equals("banned")) {
			checker = "banned";
		}
		if (checker.equalsIgnoreCase("banned")) {
			req.getRequestDispatcher("/banneduser.jsp")
					.forward(req, resp);
		} else if (checker.equalsIgnoreCase("active")) {
			session.setAttribute("login", login);
			session.setAttribute("password", password);
			req.getRequestDispatcher("/pricelist.jsp").forward(req, resp);
		} else if (checker.equalsIgnoreCase("admin")) {
			session.setAttribute("login", login);
			session.setAttribute("password", password);
			req.getRequestDispatcher("/adminpage.jsp").forward(req, resp);
		}
	}


	private String checkUser(String login, String password) {
		String result = "active";
		UserService userService = new UserService(login, password);
		result = userService.checkBanInBase();
		return result;
	}
}
