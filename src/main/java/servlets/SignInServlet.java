package servlets;

import entities.User;
import lombok.extern.log4j.Log4j2;
import services.UserCheckPasswordService;
import services.UserStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@WebServlet(name = "SignIn", value = "/sign_in")
public class SignInServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserStatus status;
        User user;
        user = (User) session.getAttribute("user");
        if (user != null) {
            status = UserStatus.valueOf(user.getStatus());
            switch (status) {
                case ACTIVE:
                    session.setAttribute("user", user);
                    resp.sendRedirect("/price_list");
                    break;
                case ADMIN:
                    session.setAttribute("user", user);
                    req.getRequestDispatcher("/adminpage.jsp").forward(req, resp);
                    break;
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
        UserStatus status;
        login = req.getParameter("login");
        password = req.getParameter("password");
        User user;
        user = UserCheckPasswordService.getInstance().checkPassword(login, password);
        if (user == null) {
            req.getRequestDispatcher("/signin.jsp").forward(req, resp);
        } else {
            status = UserStatus.valueOf(user.getStatus());
            switch (status) {
                case BANNED:
                    req.getRequestDispatcher("/banneduser.jsp").forward(req, resp);
                    break;
                case ACTIVE:
                    session.setAttribute("user", user);
                    resp.sendRedirect("/price_list");
                    break;
                case ADMIN:
                    session.setAttribute("user", user);
                    req.getRequestDispatcher("/adminpage.jsp").forward(req, resp);
                    break;
            }
        }
    }

}
