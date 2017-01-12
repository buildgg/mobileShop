package ua.home.mobileshop.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by vov on 12.01.2017.
 */
public interface ResultSetHandler<T> {
    T handle(ResultSet rs) throws SQLException;
}
