package ua.home.mobileshop.entity;

/**
 * Created by vov on 12.01.2017.
 */
public class OrderItem extends AbstractEntity<Long> {
    private Long idOrder;
    private Product product;
    private Integer count;

    public OrderItem(Product product, Integer count) {
        this.product = product;
        this.count = count;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "idOrder=" + idOrder +
                ", product=" + product +
                ", count=" + count +
                '}';
    }
}
