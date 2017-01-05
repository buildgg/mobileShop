package ua.home.mobileshop.temp;

import ua.home.mobileshop.filter.AbstractFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebFilter(urlPatterns = "/")
public class LoadPropertiesFilter extends AbstractFilter {
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        Properties prop = new Properties();
        prop.load(LoadPropertiesFilter.class.getClassLoader().getResourceAsStream("application.properties"));
        Boolean isProdaction = Boolean.valueOf(prop.getProperty("application.prodaction"));
        System.out.println("PROPERTIES = " + isProdaction);

    }
}
