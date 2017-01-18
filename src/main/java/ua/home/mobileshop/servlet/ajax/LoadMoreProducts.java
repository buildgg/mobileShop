package ua.home.mobileshop.servlet.ajax;

import ua.home.mobileshop.entity.Product;
import ua.home.mobileshop.servlet.AbstractController;
import ua.home.mobileshop.util.ConstantsView;
import ua.home.mobileshop.util.Route;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by vov on 10.01.2017.
 */
@WebServlet("/ajax/html/more/products")
public class LoadMoreProducts extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageParam = getParamPage(req);
        List<Product> productList = getProductService().getAllProducts(pageParam, ConstantsView.MAX_PRODUCTS_ON_PAGE);
        req.setAttribute(ConstantsView.ATTRIBUTE_PRODUCTS, productList);
        Route.forwardToFragment("product-list.jsp",req,resp);
    }
}
