package servlets;

import entities.CartCase;
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
import java.util.List;

@Log4j2()
@WebServlet(name = "Cart", value = "/cart")
public class CartServlet extends HttpServlet {

    private static ReserveService reserveService = ReserveService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
        HttpSession session = req.getSession();
        User        user;
        user = (User) session.getAttribute("user");
        if (user != null) {
            List<CartCase> cart = reserveService.getCart(user.getId());
            req.setAttribute("userCart", cart);
            log.info("\nCUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId() + ""
                     + " \n"
                     + "and ThreadName = " + Thread.currentThread().getName()
                     + "\nmessage is\nuserCart " + cart.toString()
                     + " created and set to request.\nUser " + user.toString()
                     + "\nredirected to cart.jsp page. + " + getClass().getName());
            req.getRequestDispatcher("/cart.jsp").forward(req, resp);
        } else {
            log.info("\nCUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId() + ""
                     + " \n"
                     + "and ThreadName = " + Thread.currentThread().getName()
                     + "\nmessage is\nUser null Redirected to signIn.jsp + "
                     + getClass().getName());
            resp.sendRedirect("sign_in");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException {
        HttpSession session = request.getSession();
        User        user;
        int         userId;
        int         goodsId;
        int         amount;
        user = (User) session.getAttribute("user");
        if (user != null) {
            log.info("\nCUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId() + ""
                     + " \n"
                     + "and ThreadName = " + Thread.currentThread().getName()
                     + "\nmessage is\nUser " + user.toString() + " got in session "
                     + session.toString());
            userId = user.getId();
            goodsId = Integer.valueOf(request.getParameter("goodsId"));
            amount = Integer.valueOf(request.getParameter("amount"));
            reserveService.reserveGoods(userId, goodsId, amount);
            log.info("\nCUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId() + ""
                     + " \n"
                     + "and ThreadName = " + Thread.currentThread().getName()
                     + "\nmessage is\nreserveGoods with userId, goodsId, amount\n" + userId + "\n"
                     + goodsId + "\n"
                     + amount + "\n created. + " + getClass().getName());
        }
        log.info("\nCUSTOM-INFO-IN-ThreadID = \n" + Thread.currentThread().getId() + ""
                 + " \n"
                 + "and ThreadName = " + Thread.currentThread().getName()
                 + "\nmessage is\nUser null Redirected to priceList.jsp + " + getClass().getName());
        response.sendRedirect("price_list");
    }
}
