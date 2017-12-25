package servlets;

import entities.User;
import lombok.extern.log4j.Log4j2;
import services.ReserveService;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user;
        int userId;
        int goodsId;
        user = (User) session.getAttribute("user");
        if (user != null) {
            log.info("User " + user.toString() + " got from session " + session.toString());
            userId = user.getId();
            goodsId = Integer.valueOf(request.getParameter("goodsId"));
            reserveService.deleteGoods(userId,goodsId);
        }
        log.info("Goods deleted from user cart.");
        response.sendRedirect("cart");}
}
