package ua.home.mobileshop.servlet.page;

import ua.home.mobileshop.servlet.AbstractController;
import ua.home.mobileshop.util.Route;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vov on 22.01.2017.
 */
@WebServlet("/sign-out")
public class SignOutController extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        Route.redirect("/products", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        Route.redirect("/products", req, resp);
    }
}
