package servlets;

import entities.User;
import services.UserCheckPasswordService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "SignIn", value = "/sign_in")
public class SignInServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        String status;
        User user;
        user = (User) session.getAttribute("user");
        if (user != null) {
            status = user.getStatus();
            if (status.equalsIgnoreCase("admin")) {
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
        String status;
        login = req.getParameter("login");
        password = req.getParameter("password");
        User user;
        user = UserCheckPasswordService.checkPassword(login, password);
        System.out.println(user);
        if (user == null) {
            req.getRequestDispatcher("/signin.jsp").forward(req, resp);
        } else {
            status = user.getStatus();
            switch (status) {
                case "banned":
                    req.getRequestDispatcher("/banneduser.jsp").forward(req, resp);
                    break;
                case "active":
                    session.setAttribute("user", user);
                    req.getRequestDispatcher("pricelist.jsp").forward(req,resp);
                    break;
                case "admin":
                    session.setAttribute("user",user);
                    req.getRequestDispatcher("/adminpage.jsp").forward(req,resp);
                    break;
            }
        }
    }
}
