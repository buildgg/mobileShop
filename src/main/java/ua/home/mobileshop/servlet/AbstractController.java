package ua.home.mobileshop.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.home.mobileshop.form.ProductForm;
import ua.home.mobileshop.form.SearchForm;
import ua.home.mobileshop.service.OrderService;
import ua.home.mobileshop.service.ProductService;
import ua.home.mobileshop.service.SocialService;
import ua.home.mobileshop.service.impl.ServiceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by vov on 04.01.2017.
 */
public abstract class AbstractController extends HttpServlet {
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private ProductService productService;
    private OrderService orderService;
    private SocialService socialService;

    private String currentPage;

    @Override
    public final void init() throws ServletException {
        productService = ServiceManager.getInstance(getServletContext()).getProductService();
        orderService = ServiceManager.getInstance(getServletContext()).getOrderService();
        socialService = ServiceManager.getInstance(getServletContext()).getSocialService();

    }

    public final ProductService getProductService() {
        return productService;
    }

    public final OrderService getOrderService() {
        return orderService;
    }

    public SocialService getSocialService() {
        return socialService;
    }

    public final int getPageCount(int totalCount, int itemsPerPage){
        int res = totalCount/itemsPerPage;
        if (res * itemsPerPage != totalCount){
            res++;
        }
        return res;
    }
    public final int getParamPage(HttpServletRequest request){
      try{
          return Integer.parseInt(request.getParameter("page"));
    }catch(NumberFormatException e){
          return 1;
      }
    }

    public final SearchForm createSearchForm(HttpServletRequest request) {
        return new SearchForm(request.getParameter("query"),
                request.getParameterValues("category"),
                request.getParameterValues("producer")
        );
    }
    public final ProductForm createProductForm(HttpServletRequest request){
        return new ProductForm(Integer.parseInt(request.getParameter("idProduct")),
                Integer.parseInt(request.getParameter("count")));
    }
}
