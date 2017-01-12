package ua.home.mobileshop.entity;

/**
 * Created by vov on 12.01.2017.
 */
public class Category extends AbstractEntity<Integer> {
    private String name;
    private String url;
    private Integer productCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", productCount=" + productCount +
                '}';
    }
}
