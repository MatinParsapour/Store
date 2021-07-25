package repository;

import java.sql.*;

public class ProductRepository {
    public void findProductId(int userId) throws SQLException {
        ProductRepository productRepository = new ProductRepository();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT goodsid FROM customerbuygoods WHERE customerid = '" + userId + "'");
        while(resultSet.next()){
            int productId = resultSet.getInt("goodsid");
            productRepository.findProducts(productId);
        }
    }
    public void findProducts(int productId) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM goods WHERE id = '" + productId + "'");
        while(resultSet.next()){
            System.out.println("id : " + resultSet.getInt("id"));
            System.out.println("name : " + resultSet.getString("name"));
            System.out.println("cost : " + resultSet.getInt("cost"));
        }
    }
}
