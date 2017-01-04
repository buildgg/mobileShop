package ua.home.mobileshop.filter;

import ua.home.mobileshop.util.Route;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vov on 04.01.2017.
 */
@WebFilter("/*")
public class ErrorHandler implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try{
            chain.doFilter(request, response);

        }catch (Throwable e){
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            String requestUri = req.getRequestURI();

            // Logerrr!!!

            Route.forwarToPage("error.jsp", req, resp);
        }

    }

    @Override
    public void destroy() {

    }
}
