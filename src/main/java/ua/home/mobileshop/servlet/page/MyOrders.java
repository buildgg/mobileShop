package ua.home.mobileshop.servlet.page;

import ua.home.mobileshop.servlet.AbstractController;
import ua.home.mobileshop.util.Route;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vov on 22.01.2017.
 */
@WebServlet("/my-orders")
public class MyOrders extends AbstractController{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Route.forwarToPage("my-orders.jsp", req, resp);
    }
}
