package ua.home.mobileshop.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vov on 03.01.2017.
 */
public final class Route {
    public static final String CURRENT_PAGE = "CURRENT_PAGE";

    public static void forwardToFragment(String jspFragment, HttpServletRequest request,
                                         HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/JSP/fragment/" + jspFragment).forward(request, response);


    }

    public static void forwarToPage(String jspPage, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(Route.CURRENT_PAGE, "page/" + jspPage);
        request.getRequestDispatcher("/WEB-INF/JSP/page-template.jsp").forward(request, response);
    }

    public static void printHtmlFragment(String htmlFragment, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().print(htmlFragment);
        response.getWriter().close();
    }

    public static void redirect(String url, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.sendRedirect(url);

    }


}
