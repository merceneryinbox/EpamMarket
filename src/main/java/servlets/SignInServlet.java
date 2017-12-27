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
            log.info("User " + user.toString() + " got from session\n" + session.toString() + ".\n");
            status = UserStatus.valueOf(user.getStatus());
            switch (status) {
                case ACTIVE:
                    session.setAttribute("user", user);
                    log.info("User " + user.toString() + " redirected to pricelist.jsp.");
                    resp.sendRedirect("/price_list");
                    break;
                case ADMIN:
                    session.setAttribute("user", user);
                    log.info("User " + user.toString() + " redirected to adminpage.jsp.");
                    req.getRequestDispatcher("/adminpage.jsp").forward(req, resp);
                    break;
            }
        } else {
            log.info("User are not present at the DB.\nRedirection to signIn.jsp page.");
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
            log.info("User null, redirect to signIn.jsp page. + " + getClass().getName());
            req.getRequestDispatcher("/signin.jsp").forward(req, resp);
        } else {
            log.info("User " + user.toString() + " got from session " + session.toString() + " + " + getClass().getName());
            status = UserStatus.valueOf(user.getStatus());
            switch (status) {
                case BANNED:
                    log.info("User " + user.toString() + " is Banned.\nRedirect to banneduser.jsp page. + " + getClass().getName());
                    req.getRequestDispatcher("/banneduser.jsp").forward(req, resp);
                    break;
                case ACTIVE:
                    session.setAttribute("user", user);
                    log.info("User " + user.toString() + " is Active.\nRedirect to priceList.jsp page." + "\nIn session " + session.toString() + " + " + getClass().getName());
                    resp.sendRedirect("/price_list");
                    break;
                case ADMIN:
                    session.setAttribute("user", user);
                    log.info("User " + user.toString() + " is Admin.\nRedirect to adminpage.jsp page." + "\nIn session " + session.toString() + " + " + getClass().getName());
                    resp.sendRedirect("/adminpage");
                    break;
            }
        }
    }
}
