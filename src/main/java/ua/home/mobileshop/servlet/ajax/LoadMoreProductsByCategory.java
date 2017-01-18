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
 * Created by vov on 13.01.2017.
 */
@WebServlet("/ajax/html/more/products/*")
public class LoadMoreProductsByCategory extends AbstractController{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String category = uri.substring("/ajax/html/more/products".length());
        int pageParam = getParamPage(req);
        List<Product> productList = getProductService().getProductsByCategory(category, pageParam, ConstantsView.MAX_PRODUCTS_ON_PAGE);

        req.setAttribute(ConstantsView.ATTRIBUTE_PRODUCTS, productList);

        int totalCount = getProductService().getCountProductsByCategory(category);
        req.setAttribute(ConstantsView.PAGE_COUNT, getPageCount(totalCount, ConstantsView.MAX_PRODUCTS_ON_PAGE));

        Route.forwardToFragment("product-list.jsp", req, resp);
    }
}
