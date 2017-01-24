package ua.home.mobileshop.servlet.page;

import ua.home.mobileshop.model.CurrentAccount;
import ua.home.mobileshop.model.SocialAccount;
import ua.home.mobileshop.servlet.AbstractController;
import ua.home.mobileshop.util.Route;
import ua.home.mobileshop.util.SessionUtils;
import ua.home.mobileshop.util.UriRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/from-social")
public class FromSocialController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        if (code != null) {
            SocialAccount socialAccount = getSocialService().getSocialAccount(code);
            CurrentAccount currentAccount = getOrderService().authentificate(socialAccount);
            SessionUtils.setCurrentAccount(currentAccount, req);
            if (SessionUtils.getCurrentPage(req) != null &&
                    UriRequest.isCurrentPageRedirect(SessionUtils.getCurrentPage(req))){
                Route.redirect(SessionUtils.getCurrentPage(req), req, resp);
            }else {
                Route.redirect("/my-orders", req, resp);
            }

        } else {
            LOGGER.error("Parameter code not found");
            Route.redirect("/sign-in", req, resp);
        }
    }
}
