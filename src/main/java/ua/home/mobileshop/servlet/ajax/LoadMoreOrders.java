package ua.home.mobileshop.servlet.ajax;

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
 * Created by vov on 25.01.2017.
 */
@WebServlet("/ajax/html/more/my-orders")
public class LoadMoreOrders extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageParam = getParamPage(req);
        CurrentAccount currentAccount = SessionUtils.getCurrentAccount(req);
        List<Order> orderList = getOrderService().getMyOrders(currentAccount, pageParam, ConstantsOrder.MAX_ORDER_PAGE);
        req.setAttribute(ConstantsOrder.ORDER_LIST, orderList);
        Route.forwardToFragment("my-orders-list.jsp", req, resp);

    }
}
