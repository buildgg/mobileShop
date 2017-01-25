package ua.home.mobileshop.servlet.page;

import ua.home.mobileshop.entity.Order;
import ua.home.mobileshop.model.ShoppingCart;
import ua.home.mobileshop.service.OrderService;
import ua.home.mobileshop.servlet.AbstractController;
import ua.home.mobileshop.util.ConstantsOrder;
import ua.home.mobileshop.util.Route;
import ua.home.mobileshop.util.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vov on 24.01.2017.
 */
@WebServlet("/order")
public class OrderController extends AbstractController {
    public static final String CURRENT_MESSAGE = "CURRENT_MESSAGE";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ShoppingCart shoppingCart = SessionUtils.getCurrentShoppingCart(req);
        long idOrder = getOrderService().makeOrder(shoppingCart, SessionUtils.getCurrentAccount(req));
        SessionUtils.clearCurrentShoppingCart(req, resp);
        req.getSession().setAttribute(CURRENT_MESSAGE, "Order created successfully, please wait for our reply");
        Route.redirect("/order?id="+ idOrder, req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String message = (String)req.getSession().getAttribute(CURRENT_MESSAGE);
        req.getSession().removeAttribute(CURRENT_MESSAGE);
        req.setAttribute(CURRENT_MESSAGE, message);
        Order order = getOrderService().findOrderById(Long.parseLong(req.getParameter("id")),
                SessionUtils.getCurrentAccount(req));
        req.setAttribute(ConstantsOrder.ORDER, order);
        Route.forwarToPage("order.jsp", req, resp);
    }
}
