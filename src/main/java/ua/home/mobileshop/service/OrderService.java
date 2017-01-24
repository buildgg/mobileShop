package ua.home.mobileshop.service;

import ua.home.mobileshop.form.ProductForm;
import ua.home.mobileshop.model.CurrentAccount;
import ua.home.mobileshop.model.ShoppingCart;
import ua.home.mobileshop.model.SocialAccount;

/**
 * Created by vov on 10.01.2017.
 */
public interface OrderService {
    void addProductToShopingCart(ProductForm productForm, ShoppingCart shoppingCart);

    void removeProductFromShoppingCart(ProductForm form, ShoppingCart shoppingCart);

    String serializeShoppingCart(ShoppingCart shoppingCart);

    ShoppingCart deserializeShoppingCart(String string);

    CurrentAccount authentificate(SocialAccount socialAccount);

}
