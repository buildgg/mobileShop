package ua.home.mobileshop.service;

import ua.home.mobileshop.entity.Category;
import ua.home.mobileshop.entity.Producer;
import ua.home.mobileshop.entity.Product;
import ua.home.mobileshop.form.SearchForm;

import java.util.List;

/**
 * Created by vov on 10.01.2017.
 */
public interface ProductService {
    List<Product> getAllProducts(int page, int limit);
    int getCountProducts();

    List<Product> getProductsByCategory(String categoryUrl, int page, int limit);

    int getCountProductsByCategory(String categoryUrl);
    List<Category> getAllCategories();

    List<Producer> getAllProducers();

    List<Product> getProductsBySearchForm(SearchForm searchForm, int page, int limit);
    int getCountProductsBySearchForm(SearchForm searchForm);

}
