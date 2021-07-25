package repository;

import entity.Goods;

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
    public void findProducts() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM goods");
        while(resultSet.next()){
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            System.out.println("id : " + resultSet.getInt("id"));
            System.out.println("name : " + resultSet.getString("name"));
            System.out.println("category : " + resultSet.getString("category"));
            System.out.println("subcategory : " + resultSet.getString("subcategory"));
            System.out.println("cost : " + resultSet.getInt("cost"));
        }
    }
    public int checkNumberOfProducts() throws SQLException {
        int countProducts = 0;
        CustomerRepository customerRepository = new CustomerRepository();
        int userId = customerRepository.findUserId();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customerbuygoods WHERE customerid = '" + userId + "'");
        while(resultSet.next()){
            countProducts ++;
        }
        return countProducts;
    }
    public void addToCart(int productId) throws SQLException {
        CustomerRepository customerRepository = new CustomerRepository();
        int userId = customerRepository.findUserId();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        PreparedStatement addToCart = connection.prepareStatement("INSERT INTO customerbuygoods (customerid,goodsid)VALUES(?,?)");
        addToCart.setInt(1,userId);
        addToCart.setInt(2,productId);
        addToCart.executeUpdate();
        Goods.setGoodsId(productId);
        int numberOfProduct = findNumberOfProduct(Goods.getGoodsId());
        decreaseInventory(numberOfProduct-1);
        System.out.println("the item added to your cart");
    }
    public boolean checkProductId(int productId) throws SQLException {
        boolean idIsCorrect = false;
        CustomerRepository customerRepository = new CustomerRepository();
        int userId= customerRepository.findUserId();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customerbuygoods WHERE customerid = '" + userId + "' and goodsid = '" + productId + "'");
        while(resultSet.next()){
            idIsCorrect = true;
        }
        return idIsCorrect;
    }
    public void deleteProduct(int productId) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        PreparedStatement deleteProduct = connection.prepareStatement("DELETE FROM customerbuygoods WHERE goodsid = ? LIMIT 1");
        deleteProduct.setInt(1,productId);
        deleteProduct.executeUpdate();
        Goods.setGoodsId(productId);
        int numberOfProduct = findNumberOfProduct(Goods.getGoodsId());
        increaseInventory(numberOfProduct+1);
        System.out.println("the product successfully deleted");
    }
    private static int findNumberOfProduct(int productId) throws SQLException {
        int numberOfProducts = 0;
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT number FROM goods WHERE id = '" + productId + "'");
        while(resultSet.next()){
            numberOfProducts = resultSet.getInt("number");
        }
        return numberOfProducts;
    }
    private static void decreaseInventory(int numberOfProduct) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        PreparedStatement decreaseInventory = connection.prepareStatement("UPDATE goods SET number = ? WHERE id = ?");
        decreaseInventory.setInt(1,numberOfProduct);
        decreaseInventory.setInt(2,Goods.getGoodsId());
        decreaseInventory.executeUpdate();
    }
    private static void increaseInventory(int numberOfProduct) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        PreparedStatement increaseInventory = connection.prepareStatement("UPDATE goods SET number = ? WHERE id = ?");
        increaseInventory.setInt(1,numberOfProduct);
        increaseInventory.setInt(2,Goods.getGoodsId());
        increaseInventory.executeUpdate();
    }
    public int checkInventory(int productId) throws SQLException {
        int inventory = 0;
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM goods WHERE id  = '" + productId + "'");
        while(resultSet.next()){
            inventory = resultSet.getInt("number");
        }
        return inventory;
    }
    public int total(int userId) throws SQLException {
        int total = 0;
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT goodsid FROM customerbuygoods WHERE customerid = '" + userId + "'");
        while(resultSet.next()){
            int productId = resultSet.getInt("goodsid");
            total += findCosts(productId);
        }
        return total;
    }
    private static int findCosts(int productId) throws SQLException {
        int cost = 0;
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT cost FROM goods WHERE id = '" + productId + "'");
        while (resultSet.next()){
            cost = resultSet.getInt("cost");
        }
        return cost;
    }

}
