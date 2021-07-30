package repository;

import entity.Goods;

import java.sql.*;

public class ProductRepository {
    private Connection connection;
    private CustomerRepository customerRepository;
    public ProductRepository(Connection connection,CustomerRepository customerRepository) {
        this.connection = connection;
        this.customerRepository = customerRepository;
    }

    public void findProductId(int userId) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT goodsid FROM customerbuygoods WHERE customerid = '" + userId + "'");
        while(resultSet.next()){
            int productId = resultSet.getInt("goodsid");
            findProducts(productId);
        }
    }
    public void findProducts(int productId) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM goods WHERE id = '" + productId + "'");
        while(resultSet.next()){
            System.out.println("id : " + resultSet.getInt("id"));
            System.out.println("name : " + resultSet.getString("name"));
            System.out.println("cost : " + resultSet.getInt("cost"));
        }
    }
    public void findProducts() throws SQLException {
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
    public int checkNumberOfProducts(int userId) throws SQLException {
        int countProducts = 0;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customerbuygoods WHERE customerid = '" + userId + "'");
        while(resultSet.next()){
            countProducts ++;
        }
        return countProducts;
    }
    public void addToCart(int productId) throws SQLException {
        int userId = customerRepository.findUserId();
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
        PreparedStatement deleteProduct = connection.prepareStatement("DELETE FROM customerbuygoods WHERE goodsid = ? LIMIT 1");
        deleteProduct.setInt(1,productId);
        deleteProduct.executeUpdate();
        Goods.setGoodsId(productId);
        int numberOfProduct = findNumberOfProduct(Goods.getGoodsId());
        increaseInventory(numberOfProduct+1);
        System.out.println("the product successfully deleted");
    }
    public int findNumberOfProduct(int productId) throws SQLException {
        int numberOfProducts = 0;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT number FROM goods WHERE id = '" + productId + "'");
        while(resultSet.next()){
            numberOfProducts = resultSet.getInt("number");
        }
        return numberOfProducts;
    }
    private void decreaseInventory(int numberOfProduct) throws SQLException {
        PreparedStatement decreaseInventory = connection.prepareStatement("UPDATE goods SET number = ? WHERE id = ?");
        decreaseInventory.setInt(1,numberOfProduct);
        decreaseInventory.setInt(2,Goods.getGoodsId());
        decreaseInventory.executeUpdate();
    }
    private void increaseInventory(int numberOfProduct) throws SQLException {
        PreparedStatement increaseInventory = connection.prepareStatement("UPDATE goods SET number = ? WHERE id = ?");
        increaseInventory.setInt(1,numberOfProduct);
        increaseInventory.setInt(2,Goods.getGoodsId());
        increaseInventory.executeUpdate();
    }
    public int checkInventory(int productId) throws SQLException {
        int inventory = 0;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM goods WHERE id  = '" + productId + "'");
        while(resultSet.next()){
            inventory = resultSet.getInt("number");
        }
        return inventory;
    }
    public int total(int userId) throws SQLException {
        int total = 0;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT goodsid FROM customerbuygoods WHERE customerid = '" + userId + "'");
        while(resultSet.next()){
            int productId = resultSet.getInt("goodsid");
            total += findCosts(productId);
        }
        return total;
    }
    private int findCosts(int productId) throws SQLException {
        int cost = 0;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT cost FROM goods WHERE id = '" + productId + "'");
        while (resultSet.next()){
            cost = resultSet.getInt("cost");
        }
        return cost;
    }
    public void insertToGoods() throws SQLException {
        PreparedStatement insertToGoods = connection.prepareStatement("INSERT INTO goods(name,category,subcategory,cost,number)VALUES(?,?,?,?,?)");
        insertToGoods.setString(1,Goods.getName());
        insertToGoods.setString(2,Goods.getCategory());
        insertToGoods.setString(3,Goods.getSubcategory());
        insertToGoods.setInt(4,Goods.getCost());
        insertToGoods.setInt(5,Goods.getNumber());
        insertToGoods.executeUpdate();
        System.out.println("the product successfully added to inventory");
    }
    public void productCategory() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT category FROM goods GROUP BY category");
        while(resultSet.next()){
            System.out.println("name : "  + resultSet.getString("category"));
        }
    }
    public boolean checkCategory(String category) throws SQLException {
        boolean categoryExists = false;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM goods WHERE category = '" + category + "'");
        while(resultSet.next()){
            categoryExists = true;
        }
        return categoryExists;
    }
    public boolean checkgoodsId(int productId) throws SQLException {
        boolean itsCorrect = false;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM goods WHERE id = '" + productId + "'");
        while(resultSet.next()){
            itsCorrect = true;
        }
        return itsCorrect;
    }
    public void deleteGoods(int productId) throws SQLException {
        PreparedStatement deleteGood = connection.prepareStatement("DELETE FROM goods WHERE id = ?");
        deleteGood.setInt(1,productId);
        deleteGood.executeUpdate();
        System.out.println("your product deleted");

    }
    public void updateInventory(int number,int productId) throws SQLException {
        PreparedStatement updateInventory = connection.prepareStatement("UPDATE goods SET number = ? WHERE id = ?");
        updateInventory.setInt(1,number);
        updateInventory.setInt(2,productId);
        updateInventory.executeUpdate();
        System.out.println("number of this product successfully changed");
    }
}
