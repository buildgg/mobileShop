package ua.home.mobileshop.servlet.page;

import ua.home.mobileshop.entity.Product;
import ua.home.mobileshop.form.SearchForm;
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
@WebServlet("/search")
public class SearhController extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      /*  SessionUtils.setCurrentPage(req);*/
        String searchUri = req.getRequestURI()+"?"+req.getQueryString();
        SessionUtils.setCurrentPage(req, searchUri);

        SearchForm searchForm = createSearchForm(req);
        List<Product> productList = getProductService().getProductsBySearchForm(searchForm, 1,
                ConstantsView.MAX_PRODUCTS_ON_PAGE);

        req.setAttribute(ConstantsView.ATTRIBUTE_PRODUCTS, productList);

        int totalCount = getProductService().getCountProductsBySearchForm(searchForm);
        int pageCount = getPageCount(totalCount, ConstantsView.MAX_PRODUCTS_ON_PAGE);
        req.setAttribute(ConstantsView.PAGE_COUNT, pageCount);

        req.setAttribute("productCount", totalCount);
        req.setAttribute(ConstantsView.SEARCHFORM, searchForm);
        Route.forwarToPage("search-result.jsp", req, resp);
    }
}
