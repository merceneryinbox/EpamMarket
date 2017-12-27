package servlets;

import entities.User;
import lombok.extern.log4j.Log4j2;
import services.ReserveService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@WebServlet(name = "CartWithDelete", value = "/cartwithdelete")
public class CartServletWithDelete extends HttpServlet {
    private static ReserveService reserveService = ReserveService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse responce)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        User        user    = (User) session.getAttribute("user");
        reserveService.deleteUserReservesAfterPayment(user.getId());
        log.info("\nCUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId() + ""
                 + " \n"
                 + "and ThreadName = " + Thread.currentThread().getName()
                 + "\nmessage is\nreserveGoods deleted after it was payed by user = \n"
                 + user.toString() +
                 "\n"
                 + " Class info --- " + getClass().getName());
        request.getRequestDispatcher("/payment.jsp").forward(request, responce);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
        HttpSession session = request.getSession();
        User        user;
        int         userId;
        int         goodsId;
        user = (User) session.getAttribute("user");
        if (user != null) {
            log.info("\nCUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId() + ""
                     + " \n"
                     + "and ThreadName = " + Thread.currentThread().getName()
                     + "\nmessage is\nUser " + user.toString() + " got from session "
                     + session.toString() + " + Class info "
                     + getClass().getName());
            userId = user.getId();
            goodsId = Integer.valueOf(request.getParameter("goodsId"));
            reserveService.deleteGoods(userId, goodsId);
        }
        log.info("\nCUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId() + ""
                 + " \n"
                 + "and ThreadName = " + Thread.currentThread().getName()
                 + "\nmessage is\nGoods deleted from user cart. + " + getClass().getName());
        response.sendRedirect("cart");
    }
}
