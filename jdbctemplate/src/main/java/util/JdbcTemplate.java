package util;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class JdbcTemplate {

    private JdbcTemplate() {
    }

    public static int execute(String url, String sql) {

        try (Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
        ) {
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new SqlMappingExeption(e);
        }
    }
}