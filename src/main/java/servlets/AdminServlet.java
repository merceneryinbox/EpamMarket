package servlets;

import entities.User;
import services.AdminService;
import services.ChangeStatusService;
import services.UserStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Admin", value = "/adminpage")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = AdminService.getUserList();
        HttpSession session = req.getSession();
        UserStatus status;
        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect("/");
        } else {
            status = UserStatus.valueOf(user.getStatus());
            switch (status) {
                case ACTIVE:
                    resp.sendRedirect("/");
                    break;
                case ADMIN:
                    try {
                        req.setAttribute("users", userList);
                        req.getRequestDispatcher("/adminpage.jsp").forward(req, resp);
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = Integer.valueOf(req.getParameter("userId"));
        ChangeStatusService.changeStatusById(id);
        resp.sendRedirect("adminpage");
    }
}
