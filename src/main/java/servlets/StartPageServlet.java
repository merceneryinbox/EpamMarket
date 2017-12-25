package servlets;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet(name = "StartPage", value = "/")
public class StartPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        log.info("doPost() method redirect request to doGet() method.");
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            log.info("User redirected to index.jsp page.");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } catch (ServletException | IOException e) {
            log.error("Dropped down at " + this.getClass() + " because of \n" + e.getMessage());
        }
    }
}
