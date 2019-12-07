package util;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class JdbcTemplate {

    private JdbcTemplate() {
    }

//    private final static String url = "jdbc:sqlite:db.sqlite";

    public static int updateProductName(String url, String table, String newName, int id) {

       String sql = "UPDATE "+ table + " SET name = ? WHERE id = ? ;";

        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new SqlMappingExeption(e);
        }
    }

    public static<T> List<T> selectProductsByPrice(String url, String sql, int minPrice, int maxPrice,
                                                   RowMapper<T> mapper) {

//        String sql = "SELECT id, name, category, quantity, isAvailable," +
//                " price FROM products WHERE price BETWEEN ? AND ? ;";

        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)

        ) {
            preparedStatement.setInt(1, minPrice);
            preparedStatement.setInt(2, maxPrice);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<T> results = new LinkedList<>();
            while (resultSet.next()) {
                results.add(mapper.map(resultSet));
//                results.add(new Product(
//                        resultSet.getInt("id"),
//                        resultSet.getString("name"),
//                        resultSet.getString("category"),
//                        resultSet.getInt("quantity"),
//                        resultSet.getBoolean("isAvailable"),
//                        resultSet.getInt("price") ));

            }
            resultSet.close();
            return results;
        } catch (SQLException e) {
            throw new SqlMappingExeption(e);
        }
    }

    public static<T> List<T> selectAllProducts(String url, String sql, RowMapper<T> mapper) {

//        String sql = "SELECT id, name, category, quantity, isAvailable," +
//                " price FROM products ;";
        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)
        ) {
            List<T> results = new LinkedList<>();
            while (resultSet.next()) {
                results.add(mapper.map(resultSet));
            }
            return results;
        } catch (SQLException e) {
            throw new SqlMappingExeption(e);
        }
    }

    public static<T> List<T> getOrdersByDateDesc(String url, String sql, RowMapper<T> mapper) {

//        String sql = "SELECT id, customer_id, product_id, " +
//                "product_name, quantity," +
//                " order_price, delivery, status," +
//                " datetime FROM orders ORDER BY datetime DESC ;";

        try (
                Connection connection = DriverManager.getConnection(url);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            List<T> orders = new LinkedList<>();
            while (resultSet.next()) {
                orders.add(mapper.map(resultSet)
//                        resultSet.getInt("id"),
//                        resultSet.getInt("customer_id"),
//                        resultSet.getInt("product_id"),
//                        resultSet.getString("product_name"),
//                        resultSet.getInt("quantity"),
//                        resultSet.getInt("order_price"),
//                        resultSet.getString("delivery"),
//                        resultSet.getBoolean("status"),
//                        resultSet.getString("datetime")
                );
            }
            return orders;
        } catch (SQLException e) {
            throw new SqlMappingExeption(e);
        }
    }
}