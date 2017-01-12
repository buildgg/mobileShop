package ua.home.mobileshop.servlet.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Created by vov on 04.01.2017.
 */
@WebServlet("/products")
public class AllProductsController extends AbstractController {
    public static final Logger LOGGER = LoggerFactory.getLogger(AllProductsController.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Product> productList = getProductService().getAllProducts(1, ConstantsView.MAX_PRODUCTS_ON_PAGE);
        LOGGER.info(":::Products List " + productList.isEmpty()+ " /n ");
        req.setAttribute(ConstantsView.ATTRIBUTE_PRODUCTS, productList);
        Route.forwarToPage("products.jsp", req, resp);

    }
}
