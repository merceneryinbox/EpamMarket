package servlets;

import dao.PostgresUserDAO;
import entities.User;
import services.UserStatus;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Registrarion", urlPatterns = "/signup")
public class SignUpServlet extends HttpServlet {
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String login;
		String password;
		String email;
		String phone;
		String statusDefault;
		User user = new User();
		PostgresUserDAO postgresUserDAO = new PostgresUserDAO();

		// added
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

		if (req != null) {
			login = (String) req.getAttribute("login");
			password = (String) req.getAttribute("password");
			email = (String) req.getAttribute("email");
			phone = (String) req.getAttribute("phone");
			statusDefault = UserStatus.ACTIVE.name();

            //added
            if (login.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                RequestDispatcher rd = req.getRequestDispatcher("signup.jsp");
                out.println("<font color=red>Please fill all the fields</font>");
                rd.include(req, resp);}
			else {
                user.setLogin(login);
                user.setPassword(password);
                user.setEmail(email);
                user.setPhone(phone);
                user.setStatus(statusDefault);
            }
			try {
				HttpSession registrationSession = req.getSession();
				registrationSession.setAttribute("user", user);
				postgresUserDAO.createNew(user);
				
				req.getRequestDispatcher("pricelist.jsp").forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}