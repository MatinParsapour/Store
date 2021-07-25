package repository;

import entity.Customer;

import java.sql.*;

public class CustomerRepository {
    public boolean checkUsernameAndPassword(String username, String password) throws SQLException {
        boolean usernameIsOk = false;
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customer");
        while(resultSet.next()){
            if(resultSet.getString("username").equals(username) && resultSet.getString("password").equals(password)){
                usernameIsOk = true;
            }
        }
        return usernameIsOk;
    }
    public boolean checkUsername(String username) throws SQLException {
        boolean exists = false;
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customer WHERE username = '" + username + "'");
        while(resultSet.next()){
            exists = true;
        }
        return  exists;
    }
    public void insertCustomer() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        PreparedStatement insertCustomer = connection.prepareStatement("INSERT INTO customer (name,username,password) VALUES (?,?,?)");
        insertCustomer.setString(1, Customer.getName());
        insertCustomer.setString(2,Customer.getUsername());
        insertCustomer.setInt(3, Integer.parseInt(Customer.getPassword()));
        insertCustomer.executeUpdate();
        System.out.println("you successfully signed up");
    }
}
