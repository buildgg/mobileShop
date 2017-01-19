package ua.home.mobileshop.filter;

import ua.home.mobileshop.util.Route;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vov on 04.01.2017.
 */
@WebFilter(filterName = "ErrorHandler")
public class ErrorHandler extends AbstractFilter /*implements Filter */ {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (Throwable e) {
            String requestUri = request.getRequestURI();
            LOGGER.error("requestUri = ::: " + requestUri + " failed ::: = " + e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            Route.forwarToPage("error.jsp", request, response);
        }

    }

}
