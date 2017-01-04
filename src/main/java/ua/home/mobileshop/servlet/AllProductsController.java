package ua.home.mobileshop.servlet;

import ua.home.mobileshop.util.Route;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by vov on 04.01.2017.
 */
@WebServlet("/products")
public class AllProductsController extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<?> products = Collections.emptyList();
        req.setAttribute("products", products);
        Route.forwarToPage("products.jsp", req, resp);
    }
}
