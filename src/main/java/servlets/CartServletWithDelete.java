package servlets;

import entities.User;
import services.ReserveService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "CartWithDelete", value = "/cartwithdelete")
public class CartServletWithDelete extends HttpServlet {
    private static ReserveService reserveService = new ReserveService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user;
        int userId;
        int goodsId;
        user = (User) session.getAttribute("user");
        if (user != null) {
            userId = user.getId();
            goodsId = Integer.valueOf(request.getParameter("goodsId"));
            reserveService.deleteGoods(userId,goodsId);
        }
        response.sendRedirect("cart");}
}
