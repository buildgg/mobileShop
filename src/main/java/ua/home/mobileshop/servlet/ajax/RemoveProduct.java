package ua.home.mobileshop.servlet.ajax;

import org.json.JSONObject;
import ua.home.mobileshop.form.ProductForm;
import ua.home.mobileshop.model.ShoppingCart;
import ua.home.mobileshop.servlet.AbstractController;
import ua.home.mobileshop.util.Route;
import ua.home.mobileshop.util.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vov on 19.01.2017.
 */
@WebServlet("/ajax/json/product/remove")
public class RemoveProduct extends AbstractController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductForm productForm = createProductForm(req);
        ShoppingCart shoppingCart = SessionUtils.getCurrentShoppingCart(req);
        getOrderService().removeProductFromShoppingCart(productForm, shoppingCart);

        if (shoppingCart.getItems().isEmpty()) {
            SessionUtils.clearCurrentShoppingCart(req, resp);
        } else {
            String cookieValue = getOrderService().serializeShoppingCart(shoppingCart);
            SessionUtils.updateCurrentShoppingCartCookie(cookieValue, resp);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("totalCount", shoppingCart.getTotalCount());
        jsonObject.put("totalCost", shoppingCart.getTotalCost());

        Route.sendJSON(jsonObject, req, resp);


    }
}
