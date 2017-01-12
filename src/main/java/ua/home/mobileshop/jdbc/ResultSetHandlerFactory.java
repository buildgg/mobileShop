package ua.home.mobileshop.jdbc;

import ua.home.mobileshop.entity.Category;
import ua.home.mobileshop.entity.Producer;
import ua.home.mobileshop.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vov on 12.01.2017.
 */
public final class ResultSetHandlerFactory {
    public final static ResultSetHandler<Product> PRODUCT_RESULT_SET_HANDLER = new ResultSetHandler<Product>() {
        @Override
        public Product handle(ResultSet rs) throws SQLException {

            Product product = new Product();
            product.setCategory(rs.getString("category"));
            product.setDescription(rs.getString("description"));
            product.setId(rs.getInt("id"));
            product.setImageLink(rs.getString("image_link"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getBigDecimal("price"));
            product.setProducer(rs.getString("producer"));
        return product;
        }
    };
    public static final ResultSetHandler<Category> CATEGORY_RESULT_SET_HANDLER = new ResultSetHandler<Category>() {
        @Override
        public Category handle(ResultSet rs) throws SQLException {
            Category category = new Category();
            category.setName(rs.getString("name"));
            //category.setId(rs.getInt("id"));
            category.setUrl(rs.getString("url"));
            category.setProductCount(rs.getInt("product_count"));
            return category;
        }
    };

    public static final ResultSetHandler<Producer> PRODUCER_RESULT_SET_HANDLER = new ResultSetHandler<Producer>() {
        @Override
        public Producer handle(ResultSet rs) throws SQLException {
            Producer producer = new Producer();
            //producer.setId(rs.getInt("id"));
            producer.setName(rs.getString("name"));
            producer.setProductCont(rs.getInt("product_count"));
            return producer;
        }
    };

    public final static <T> ResultSetHandler<T> getSingleResultSetHandler(final ResultSetHandler<T> oneRowResultSetHandler) {
        return new ResultSetHandler<T>() {
            @Override
            public T handle(ResultSet rs) throws SQLException {
                if (rs.next()) {
                    return oneRowResultSetHandler.handle(rs);
                } else {
                    return null;
                }
            }
        };
    }

    public final static <T> ResultSetHandler<List<T>> getListResultSetHandler
            (final ResultSetHandler<T> oneRowResultSetHandler) {
        return new ResultSetHandler<List<T>>() {

            @Override
            public List<T> handle(ResultSet rs) throws SQLException {
                List<T> list = new ArrayList<>();
                while (rs.next()) {
                    list.add(oneRowResultSetHandler.handle(rs));
                }
                return list;
            }
        };
    }
}
