package ua.home.mobileshop.servlet.ajax;

import ua.home.mobileshop.entity.Product;
import ua.home.mobileshop.form.SearchForm;
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
 * Created by vov on 17.01.2017.
 */
@WebServlet("/ajax/html/more/search")
public class SearchResultsMore extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SearchForm searchForm = createSearchForm(req);
        int pageParam = getParamPage(req);
        List<Product> productList = getProductService().getProductsBySearchForm(searchForm, pageParam,
                ConstantsView.MAX_PRODUCTS_ON_PAGE);

        req.setAttribute(ConstantsView.ATTRIBUTE_PRODUCTS, productList);
        Route.forwardToFragment("product-list.jsp", req, resp);
    }
}
