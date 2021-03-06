package ua.home.mobileshop.servlet.page;

import ua.home.mobileshop.entity.Product;
import ua.home.mobileshop.servlet.AbstractController;
import ua.home.mobileshop.util.ConstantsView;
import ua.home.mobileshop.util.Route;
import ua.home.mobileshop.util.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by vov on 13.01.2017.
 */
@WebServlet("/products/*")
public class ProductsByCategory extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionUtils.setCurrentPage(req);
        String uri = req.getRequestURI();
        String category = uri.substring("products/".length());

        List<Product> productList = getProductService().getProductsByCategory(category, 1, ConstantsView.MAX_PRODUCTS_ON_PAGE);
        req.setAttribute(ConstantsView.ATTRIBUTE_PRODUCTS, productList);
        req.setAttribute(ConstantsView.SELECTED_CATEGORY_URL, category);
        int totalCount = getProductService().getCountProductsByCategory(category);
        req.setAttribute(ConstantsView.PAGE_COUNT, getPageCount(totalCount, ConstantsView.MAX_PRODUCTS_ON_PAGE));

        Route.forwarToPage("products.jsp", req, resp);

    }
}
