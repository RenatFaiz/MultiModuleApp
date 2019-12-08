package util;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class JdbcTemplate {

    private JdbcTemplate() {
    }

    public static int updateName(String url, String table, String newName, int id) {

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

    public static<T> List<T> selectByParameters(String url, String sql, int param1,
                                                int param2, RowMapper<T> mapper) {

        try (Connection connection = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, param1);
            preparedStatement.setInt(2, param2);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<T> results = new LinkedList<>();
            while (resultSet.next()) {
                results.add(mapper.map(resultSet));
            }
            resultSet.close();
            return results;
        } catch (SQLException e) {
            throw new SqlMappingExeption(e);
        }
    }

    public static<T> List<T> executeQuery(String url, String sql, RowMapper<T> mapper) {

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
}