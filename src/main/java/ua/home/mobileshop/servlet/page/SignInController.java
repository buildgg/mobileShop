package ua.home.mobileshop.servlet.page;

import ua.home.mobileshop.servlet.AbstractController;
import ua.home.mobileshop.util.Route;
import ua.home.mobileshop.util.SessionUtils;
import ua.home.mobileshop.util.UriRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vov on 22.01.2017.
 */
@WebServlet("/sign-in")
public class SignInController extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GET");
        if (SessionUtils.isCurrentAccountCreated(req)) {
           if (UriRequest.isCurrentPageRedirect(SessionUtils.getCurrentPage(req))){
               Route.redirect(SessionUtils.getCurrentPage(req), req, resp);
           } else {
               Route.redirect("/my-orders", req, resp);
           }

        } else {
            Route.forwarToPage("sign-in.jsp", req, resp);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (SessionUtils.isCurrentAccountCreated(req)) {
            if (SessionUtils.getCurrentPage(req) != null &&
                    UriRequest.isCurrentPageRedirect(SessionUtils.getCurrentPage(req))) {
                Route.redirect(SessionUtils.getCurrentPage(req), req, resp);
            }else {
                System.out.println("POST YES");
                Route.redirect("/my-orders", req, resp);
            }

        } else {
            System.out.println("POST NO");
            System.out.println(SessionUtils.getCurrentPage(req));
            System.out.println(getSocialService().getAuthorizeUrl());
            Route.redirect(getSocialService().getAuthorizeUrl(), req, resp);
        }
    }
}
