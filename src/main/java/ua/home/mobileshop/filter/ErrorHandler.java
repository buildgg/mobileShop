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
@WebFilter(urlPatterns = "/")
public class ErrorHandler extends AbstractFilter implements Filter {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("HEllo");
        try{
            chain.doFilter(request, response);
        }catch (Throwable e){

            String requestUri = request.getRequestURI();

            // Logerrr!!!

            Route.forwarToPage("error.jsp", request, response);
        }

    }

}
