package ua.home.mobileshop.service;

import ua.home.mobileshop.entity.Category;
import ua.home.mobileshop.entity.Producer;
import ua.home.mobileshop.entity.Product;

import java.util.List;

/**
 * Created by vov on 10.01.2017.
 */
public interface ProductService {
    List<Product> getAllProducts(int page, int limit);
    List<Product> getProductsByCategory(String categoryUrl, int page, int limit);

    List<Category> getAllCategories();

    List<Producer> getAllProducers();

}
