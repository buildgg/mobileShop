package ua.home.mobileshop.servlet;

import ua.home.mobileshop.service.OrderService;
import ua.home.mobileshop.service.ProductService;
import ua.home.mobileshop.service.impl.ServiceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Created by vov on 04.01.2017.
 */
public abstract class AbstractController extends HttpServlet {
    private ProductService productService;
    private OrderService orderService;

    @Override
    public final void init() throws ServletException {
        productService = ServiceManager.getInstance(getServletContext()).getProductService();
        orderService = ServiceManager.getInstance(getServletContext()).getOrderService();
    }

    public final ProductService getProductService() {
        return productService;
    }

    public final OrderService getOrderService() {
        return orderService;
    }
}
