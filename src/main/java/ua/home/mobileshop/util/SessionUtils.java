package ua.home.mobileshop.util;

import ua.home.mobileshop.model.ShoppingCart;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by vov on 18.01.2017.
 */
public class SessionUtils {
    public static ShoppingCart getCurrentShoppingCart(HttpServletRequest req) {
        ShoppingCart shoppingCart = (ShoppingCart) req.getSession().getAttribute(ConstantsOrder.CURRENT_SHOPPING_CART);
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            setCurrentShoppingCart(req, shoppingCart);
        }
        return shoppingCart;
    }
    public static void setCurrentShoppingCart(HttpServletRequest req, ShoppingCart shoppingCart) {
        req.getSession().setAttribute(ConstantsOrder.CURRENT_SHOPPING_CART, shoppingCart);
    }
    public static boolean isCurrentShoppingCartCreated(HttpServletRequest req) {
        return req.getSession().getAttribute(ConstantsOrder.CURRENT_SHOPPING_CART) != null;
    }
    public static void updateCurrentShoppingCartCookie(String cookieValue, HttpServletResponse resp) {
        WebUtils.setCookie(ConstantsOrder.Cookie.SHOPPING_CART.getName(), cookieValue,
                ConstantsOrder.Cookie.SHOPPING_CART.getTtl(), resp);
    }
    public static void clearCurrentShoppingCart(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().removeAttribute(ConstantsOrder.CURRENT_SHOPPING_CART);
        WebUtils.setCookie(ConstantsOrder.Cookie.SHOPPING_CART.getName(), null, 0, resp);
    }
    public static Cookie findShoppingCartCookie(HttpServletRequest req) {
        return WebUtils.findCookie(req, ConstantsOrder.Cookie.SHOPPING_CART.getName());
    }

}
