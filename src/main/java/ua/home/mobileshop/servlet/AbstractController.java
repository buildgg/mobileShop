package ua.home.mobileshop.servlet;

import ua.home.mobileshop.form.SearchForm;
import ua.home.mobileshop.service.OrderService;
import ua.home.mobileshop.service.ProductService;
import ua.home.mobileshop.service.impl.ServiceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

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
}
