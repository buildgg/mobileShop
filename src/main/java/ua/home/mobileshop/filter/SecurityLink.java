package ua.home.mobileshop.filter;

import ua.home.mobileshop.util.Route;
import ua.home.mobileshop.util.SessionUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vov on 23.01.2017.
 */
@WebFilter(filterName = "SecurityLink")
public class SecurityLink extends AbstractFilter{
public static final String URI_USER = "URI_USER";
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (SessionUtils.isCurrentAccountCreated(request)){
            chain.doFilter(request, response);
        }else{
            request.getSession().setAttribute(URI_USER, request.getRequestURI());
            Route.redirect("/sign-in", request, response);
            //chain.doFilter(request, response);
        }
    }
}















