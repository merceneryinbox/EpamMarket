package servlets;

import entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Admin", value = "/admin")
public class AdminServlet extends HttpServlet{

    class AdminService {
        public List<User> getUserList(){
            throw new UnsupportedOperationException();
        }
    }
    AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = adminService.getUserList();
        try {
            req.setAttribute("users", userList);
            req.getRequestDispatcher("/adminpage.jsp").forward(req, resp);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
