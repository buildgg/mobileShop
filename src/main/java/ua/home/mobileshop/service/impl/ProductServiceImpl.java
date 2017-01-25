package ua.home.mobileshop.service.impl;

import ua.home.mobileshop.entity.Category;
import ua.home.mobileshop.entity.Producer;
import ua.home.mobileshop.entity.Product;
import ua.home.mobileshop.exeption.InternalServerErrorException;
import ua.home.mobileshop.form.SearchForm;
import ua.home.mobileshop.jdbc.JDBCUtils;
import ua.home.mobileshop.jdbc.ResultSetHandler;
import ua.home.mobileshop.jdbc.ResultSetHandlerFactory;
import ua.home.mobileshop.service.ProductService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vov on 10.01.2017.
 */
class ProductServiceImpl implements ProductService {
    public static final ResultSetHandler<List<Category>> categoryResultSetHandler =
            ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.CATEGORY_RESULT_SET_HANDLER);
    public static final ResultSetHandler<List<Producer>> producerResultSetHandler =
            ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.PRODUCER_RESULT_SET_HANDLER);
    public static final ResultSetHandler<Integer> productCountResultSet = ResultSetHandlerFactory.getCountResultSetHandler();
    private static final ResultSetHandler<List<Product>> productsResultSetHandler =
            ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.PRODUCT_RESULT_SET_HANDLER);
    private DataSource dataSource;

    public ProductServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> getAllProducts(int page, int limit) {
        try (Connection c = dataSource.getConnection()) {
            int offset = (page - 1) * limit;
            return JDBCUtils.select(c, "select p.*, c.name as category, pr.name as producer " +
                            "from product p, producer pr, category c " +
                            "where c.id=p.id_category and pr.id=p.id_producer limit ? offset ?",
                    productsResultSetHandler, limit, offset);
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute sql query " + e.getMessage(), e);
        }

    }

    @Override
    public List<Product> getProductsByCategory(String categoryUrl, int page, int limit) {
        try (Connection c = dataSource.getConnection()) {
            int offset = (page - 1) * limit;
            return JDBCUtils.select(c, "select p.*, cat.name as category, pr.name as producer " +
                            "from product p, producer pr, category cat " +
                            "where p.id_category=cat.id and p.id_producer=pr.id " +
                            "and cat.url= ? limit ? offset ?",
                    productsResultSetHandler, categoryUrl, limit, offset);


        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute sql ByCategory " + e.getMessage(), e);
        }
    }

    @Override
    public List<Category> getAllCategories() {
        try (Connection c = dataSource.getConnection()) {
            return JDBCUtils.select(c, "select name, url, product_count, id from category order by name", categoryResultSetHandler);
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't sql getCategory " + e.getMessage(), e);
        }
    }

    @Override
    public List<Producer> getAllProducers() {
        try (Connection c = dataSource.getConnection()) {
            return JDBCUtils.select(c, "select name, product_count, id from producer order by name", producerResultSetHandler);
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't sql getProducer " + e.getMessage(), e);
        }
    }

    @Override
    public int getCountProducts() {
        try (Connection c = dataSource.getConnection()) {
            return JDBCUtils.select(c, "select count(*) from product", productCountResultSet);
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute sql query " + e.getMessage(), e);
        }
    }

    @Override
    public int getCountProductsByCategory(String categoryUrl) {
        try (Connection c = dataSource.getConnection()) {
            return JDBCUtils.select(c, "select count(*) " +
                    "from product p, category cat " +
                            "where p.id_category=cat.id and cat.url= ?",
                    productCountResultSet, categoryUrl);
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute sql query " + e.getMessage(), e);
        }
    }

    @Override
    public List<Product> getProductsBySearchForm(SearchForm searchForm, int page, int limit) {
        try (Connection c = dataSource.getConnection()) {
            int offset = (page - 1) * limit;
            return JDBCUtils.select(c, "select p.*, c.name as category, pr.name as producer " +
                            "from product p, producer pr, category c " +
                            "where (p.name ilike ? or p.description ilike ?) and c.id=p.id_category and pr.id=p.id_producer limit ? offset ?",
                    productsResultSetHandler,"%"+searchForm.getQuery()+"%", "%"+searchForm.getQuery()+"%", limit, offset);
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute sql query " + e.getMessage(), e);
        }
    }

    @Override
    public int getCountProductsBySearchForm(SearchForm searchForm) {
        Integer[] categories = new Integer[searchForm.getCategories().size()];
        categories = searchForm.getCategories().toArray(categories);
        String s = "2,3";



        try (Connection c = dataSource.getConnection()) {
            return JDBCUtils.select(c, "select count(*) " +
                            "from product p, producer pr, category c " +
                            "where (p.name ilike ? or p.description ilike ?) " +
                            "and c.id in (2,3) " +
                            "and c.id=p.id_category and pr.id=p.id_producer",
                    productCountResultSet,"%"+searchForm.getQuery()+"%",
                    "%"+searchForm.getQuery()+"%");

        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute sql query " + e.getMessage(), e);
        }
    }
}
