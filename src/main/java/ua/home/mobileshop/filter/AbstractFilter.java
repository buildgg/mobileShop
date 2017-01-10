package ua.home.mobileshop.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.home.mobileshop.util.UriRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractFilter implements Filter {
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public final void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        if (UriRequest.isMediaUrl(uri) || UriRequest.isStaticUrl(uri)) {
            chain.doFilter(req, resp);
        } else {
            doFilter(req, resp, chain);
        }
    }

    public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException;
     /*    System.out.println("::::DO 2");*/

    @Override
    public void destroy() {

    }
}
