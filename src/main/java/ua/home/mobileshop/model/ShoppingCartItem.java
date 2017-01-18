package ua.home.mobileshop.model;

import ua.home.mobileshop.entity.Product;

/**
 * Created by vov on 12.12.2016.
 */
public class ShoppingCartItem {
    private Product product;
    private int count;

    public ShoppingCartItem(Product product, int count) {
        this.product = product;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    @Override
    public String toString() {
        return "ShoppingCartItem{" +
                "product= " + product +
                ", count= " + count +
                '}';
    }

}
