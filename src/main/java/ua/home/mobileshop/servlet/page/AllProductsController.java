package ua.home.mobileshop.servlet.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Created by vov on 04.01.2017.
 */
@WebServlet("/products")
public class AllProductsController extends AbstractController {
    public static final Logger LOGGER = LoggerFactory.getLogger(AllProductsController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
      /*  int pageParam = getParamPage(req);*/
        SessionUtils.setCurrentPage(req);

        List<Product> productList = getProductService().getAllProducts(1, ConstantsView.MAX_PRODUCTS_ON_PAGE);
        req.setAttribute(ConstantsView.ATTRIBUTE_PRODUCTS, productList);

        int totalCount = getProductService().getCountProducts();
        int pageCount = getPageCount(totalCount, ConstantsView.MAX_PRODUCTS_ON_PAGE);
        req.setAttribute(ConstantsView.PAGE_COUNT, pageCount);

        Route.forwarToPage("products.jsp", req, resp);

    }
}
