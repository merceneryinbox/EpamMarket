package servlets;

import entities.User;
import services.AdminService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Admin", value = "/adminpage")
public class AdminServlet extends HttpServlet{


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = AdminService.getUserList();
        try {
            req.setAttribute("users", userList);
            req.getRequestDispatcher("/adminpage.jsp").forward(req, resp);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
