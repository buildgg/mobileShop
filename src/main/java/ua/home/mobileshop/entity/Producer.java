package ua.home.mobileshop.entity;

/**
 * Created by vov on 11.01.2017.
 */
public class Producer extends AbstractEntity<Integer> {
    private String name;
    private int productCont;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductCont() {
        return productCont;
    }

    public void setProductCont(int productCont) {
        this.productCont = productCont;
    }
}
