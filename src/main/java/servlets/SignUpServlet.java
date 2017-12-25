package servlets;

import dao.PostgresUserDAO;
import entities.User;
import lombok.extern.log4j.Log4j2;
import services.UserRegistrator;
import services.UserStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Log4j2
@WebServlet(name = "Registrarion", value = "/sign_up")
public class SignUpServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
			ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			log.info("Redirect user for registration.");
			request.getRequestDispatcher("/signup.jsp").forward(request, response);
		} else {
			log.info("Approve existing user and redirect him to index.jsp");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		log.info("Test info in SignUpServlet ", getClass().getName());
		String login;
		String password;
		String email;
		String phone;
		String statusDefault;
		User user = new User();
		Optional<User> optionalUser;
		PostgresUserDAO postgresUserDAO = PostgresUserDAO.getInstance();

		try {
			if (request != null) {
				login =  request.getParameter("login");
				password = request.getParameter("password");
				email = request.getParameter("email");
				phone = request.getParameter("phone");
				statusDefault = UserStatus.ACTIVE.name();

				if (!postgresUserDAO.getUserByLogin(login).isPresent()) {
					log.info("Positive answer from request with user's fields got " + login);
					if (login != null && password != null) {
						log.info("Not null login and password detected.");

						user.setLogin(login);
						user.setPassword(password);
						user.setEmail(email == null ? "n@email" : email);
						user.setPhone(phone == null ? "no phone" : phone);
						user.setStatus(statusDefault);

						if (UserRegistrator.registrate(user)){
							HttpSession registrationSession = request.getSession();
						log.info("Create not existing user, push him into db and to the "
								+ "HttpSession"
								+ " - registrationSession - " + registrationSession.toString());

              postgresUserDAO.createNew(user);
						optionalUser = postgresUserDAO.getUserByLogin(login);
						if (optionalUser.isPresent()) {
							log.info("New created User with ID return from DB - " + user.toString());
							registrationSession.setAttribute("user", optionalUser.get());
							response.sendRedirect("/price_list");
						} else {
							log.info("Redirect user" + user.getClass().getSimpleName()
									+ " to registration page.");
							request.getRequestDispatcher("/signup.jsp").forward(request,response);
						    }
						} else {
							request.getRequestDispatcher("/signup.jsp").forward(request,response);
						}
					} else {
						log.info("Redirect user" + user.getClass().getSimpleName()
								+ " to registration page.");
						request.getRequestDispatcher("/signup.jsp").forward(request, response);
					}
				} else {
					request.getRequestDispatcher("/signup.jsp").forward(request, response);
					request.setAttribute("tag",login);
				}
			}
		} catch (ServletException | IOException serve) {
			log.error("Droped down at " + getClass().getSimpleName() + " because of \n"
					+ serve.getMessage());
		}
	}
}