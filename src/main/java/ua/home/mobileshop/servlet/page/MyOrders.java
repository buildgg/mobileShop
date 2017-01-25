package ua.home.mobileshop.servlet.page;

import ua.home.mobileshop.entity.Order;
import ua.home.mobileshop.model.CurrentAccount;
import ua.home.mobileshop.servlet.AbstractController;
import ua.home.mobileshop.util.ConstantsOrder;
import ua.home.mobileshop.util.Route;
import ua.home.mobileshop.util.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by vov on 22.01.2017.
 */
@WebServlet("/my-orders")
public class MyOrders extends AbstractController{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CurrentAccount currentAccount = SessionUtils.getCurrentAccount(req);
        int countOrders = getOrderService().getCountMyOrders(currentAccount);
        List<Order> orderList = getOrderService().getMyOrders(currentAccount, 1, ConstantsOrder.MAX_ORDER_PAGE);
        int pageCount = getPageCount(countOrders, ConstantsOrder.MAX_ORDER_PAGE);
        req.setAttribute(ConstantsOrder.PAGE_COUNT_ORDER, pageCount);
        req.setAttribute(ConstantsOrder.ORDER_LIST, orderList);
        Route.forwarToPage("my-orders.jsp", req, resp);
    }
}
