package ua.home.mobileshop.servlet.page;

import ua.home.mobileshop.servlet.AbstractController;
import ua.home.mobileshop.util.Route;
import ua.home.mobileshop.util.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vov on 19.01.2017.
 */
@WebServlet("/shopping-cart")
public class ShowShoppingCart extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionUtils.setCurrentPage(req);
       if (SessionUtils.isCurrentShoppingCartCreated(req)){
        Route.forwarToPage("shopping-cart.jsp", req, resp);
       }else {
           Route.forwarToPage("products.jsp", req, resp);
       }
    }
}