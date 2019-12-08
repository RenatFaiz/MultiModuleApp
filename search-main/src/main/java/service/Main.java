package service;

import model.Product;
import util.JdbcTemplate;
import util.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:db.sqlite";

      //  String sql = "UPDATE products SET name = ? WHERE id = ? ;";

        System.out.println(JdbcTemplate.updateName("jdbc:sqlite:db.sqlite", "products",
                "iphone", 0));
        System.out.println(JdbcTemplate.updateName("jdbc:sqlite:db.sqlite", "products",
                "Микроволновая печь V-HOME P70H20L-KH", 0));


        String sql2 = "SELECT id, name, category, quantity, isAvailable," +
                " price FROM products WHERE price BETWEEN ? AND ? ;";
//        String sql2 = "SELECT id, name, category, quantity, isAvailable," +
//                " price FROM products WHERE price > ? AND isAvailable = ?;";
        System.out.println(JdbcTemplate.selectByParameters(url, sql2, 100, 5000,
                new RowMapper<Product>() {
                    @Override
                    public Product map(ResultSet rs) throws SQLException {
                        return new Product(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("category"),
                                rs.getInt("quantity"),
                                rs.getBoolean("isAvailable"),
                                rs.getInt("price")
                        );
                    }
                }));


        String x = " products;";
        String sql3 = "SELECT id, name, category, quantity, isAvailable," +
                " price FROM" + x;
      //  System.out.println(sql3 = sql3.concat(" products;"));

        System.out.println(JdbcTemplate.executeQuery(url, sql3,
                new RowMapper<Product>() {
                    @Override
                    public Product map(ResultSet rs) throws SQLException {
                        return new Product(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("category"),
                                rs.getInt("quantity"),
                                rs.getBoolean("isAvailable"),
                                rs.getInt("price")
                        );
                    }
                }));


        String sql4 = "SELECT id, customer_id, product_id, " +
                "product_name, quantity," +
                " order_price, delivery, status," +
                " datetime FROM orders ORDER BY datetime DESC ;";

        System.out.println(JdbcTemplate.executeQuery(url, sql4,
                new RowMapper<Order>() {
                    @Override
                    public Order map(ResultSet rs) throws SQLException {
                        return new Order(
                                rs.getInt("id"),
                                rs.getInt("customer_id"),
                                rs.getInt("product_id"),
                                rs.getString("product_name"),
                                rs.getInt("quantity"),
                                rs.getInt("order_price"),
                                rs.getString("delivery"),
                                rs.getBoolean("status"),
                                rs.getString("datetime")

                        );
                    }
        }));
    }
}
