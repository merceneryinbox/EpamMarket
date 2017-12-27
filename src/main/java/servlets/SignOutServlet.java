package servlets;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@WebServlet(name = "SignOut", value = "/out")
public class SignOutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("user", null);
        log.info(" CUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId() + ""
                 + " \n"
                 + "and ThreadName = " + Thread.currentThread().getName()
                 + "\nmessage is\nUser destroyed.\nConnection redirected by response to index.jsp"
                 + getClass().getName());
        resp.sendRedirect("/");
    }
}
