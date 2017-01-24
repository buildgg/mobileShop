package ua.home.mobileshop.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by vov on 12.01.2017.
 */
public final class JDBCUtils {
    public static <T> T select(Connection c, String sql, ResultSetHandler<T> resultSetHandler, Object ...parameters) throws SQLException {
        try(PreparedStatement ps = c.prepareStatement(sql)){
            populatePreparedStatement(ps, parameters);
            ResultSet rs = ps.executeQuery();

            return resultSetHandler.handle(rs);


        }
    }
    private static void populatePreparedStatement(PreparedStatement ps, Object...parameters) throws SQLException {
        if (parameters != null){
            for (int i = 0; i < parameters.length; i++){
                ps.setObject(i + 1, parameters[i]);
            }
        }
    }
    public static <T> T insert(Connection c, String sql, ResultSetHandler<T> resultSetHandler, Object... parameters) throws SQLException {
        try (PreparedStatement ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            populatePreparedStatement(ps, parameters);
            int result = ps.executeUpdate();
            if (result != 1) {
                throw new SQLException("Can't insert row to database. Result=" + result);
            }
            ResultSet rs = ps.getGeneratedKeys();
            return resultSetHandler.handle(rs);
        }
    }
    private JDBCUtils() {
    }
}
