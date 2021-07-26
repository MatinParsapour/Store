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
    public int findUserId() throws SQLException {
        int userId = 0;
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id FROM customer WHERE username = '" + Customer.getUsername() + "'");
        while(resultSet.next()){
            userId = resultSet.getInt("id");
        }
        return userId;
    }
    public int findCurrentBalance(int userId) throws SQLException {
        int currentBalance = 0;
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT balance FROM customer WHERE id = '" + userId + "'");
        while(resultSet.next()){
            currentBalance = resultSet.getInt("balance");
        }
        return currentBalance;
    }
    public void updateUserBalance(int amount, int userId) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        PreparedStatement updateUserBalance = connection.prepareStatement("UPDATE customer SET balance = ? WHERE id = ?");
        updateUserBalance.setInt(1,amount);
        updateUserBalance.setInt(2,userId);
        updateUserBalance.executeUpdate();
        System.out.println("you account successfully changed");
    }
    public void findPassword() throws SQLException {
        int userId = findUserId();
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customer WHERE id = '" + userId + "'");
        while(resultSet.next()){
            System.out.println("your password : " + resultSet.getInt("password"));
        }
    }
    public void changePassword(int password) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        int userId = findUserId();
        PreparedStatement changePassword = connection.prepareStatement("UPDATE customer SET password = ? WHERE id = ?");
        changePassword.setInt(1,password);
        changePassword.setInt(2,userId);
        changePassword.executeUpdate();
        System.out.println("your password changed");
    }
    public void changeUsername(int userId) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        PreparedStatement changeUsername = connection.prepareStatement("UPDATE customer SET username = ? WHERE id = ?");
        changeUsername.setString(1,Customer.getUsername());
        changeUsername.setInt(2,userId);
        changeUsername.executeUpdate();
        System.out.println("your username changed");
    }
    public void clearCart(int userId) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        PreparedStatement clearCart = connection.prepareStatement("DELETE FROM customerbuygoods WHERE customerid = ?");
        clearCart.setInt(1,userId);
        clearCart.executeUpdate();
        System.out.println("your cart is now empty");
    }
    public boolean checkCart(int userId) throws SQLException {
        boolean cartChecked = false;
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT customerid FROM customerbuygoods WHERE customerid = '" + userId + "'");
        while(resultSet.next()){
            cartChecked = true;
            break;
        }
        return cartChecked;
    }
    public boolean checkVerify(int userId) throws SQLException {
        boolean userIsVerify = false;
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT verify FROM customer WHERE id = '" + userId + "'");
        while(resultSet.next()){
            if(resultSet.getInt("verify") == 1){
                userIsVerify = true;
            }
        }
        return userIsVerify;
    }
    public void findUnverifiedPeople() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customer WHERE verify = 0 ");
        while(resultSet.next()){
            System.out.println("id : " + resultSet.getInt("id"));
            System.out.println("name : " + resultSet.getString("name"));
            System.out.println("username : " + resultSet.getString("username"));
            System.out.println("status : " + resultSet.getInt("verify"));
        }
    }
    public boolean checkCustomerStatusForVerify(int customerId) throws SQLException {
        boolean canVerify = false;
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT verify FROM customer WHERE id = '" + customerId + "'");
        while(resultSet.next()){
            if(resultSet.getInt("verify") == 0){
                canVerify = true;
            }
        }
        return canVerify;
    }
    public void verifyPerson(int customerId) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        PreparedStatement verifyPerson = connection.prepareStatement("UPDATE customer SET verify = 1 WHERE id = ?");
        verifyPerson.setInt(1,customerId);
        verifyPerson.executeUpdate();
        System.out.println("the customer successfully verified");
    }
    public void findVerifiedPeople() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customer WHERE verify = 1 AND id > 1");
        while(resultSet.next()){
            System.out.println("id : " + resultSet.getInt("id"));
            System.out.println("name : " + resultSet.getString("name"));
            System.out.println("username : " + resultSet.getString("username"));
            System.out.println("status : " + resultSet.getInt("verify"));
        }
    }
    public boolean checkCustomerStatusForSuspend(int customerId) throws SQLException {
        boolean canVerify = false;
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT verify FROM customer WHERE id = '" + customerId + "'");
        while(resultSet.next()){
            if(resultSet.getInt("verify") == 1){
                canVerify = true;
            }
        }
        return canVerify;
    }
    public void suspendPerson(int customerId) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        PreparedStatement suspendPerson = connection.prepareStatement("UPDATE customer SET verify = 2 WHERE id = ? ");
        suspendPerson.setInt(1,customerId);
        suspendPerson.executeUpdate();
        System.out.println("person successfully suspended");
    }
    public void findSuspendedPeople() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customer WHERE verify = 2 AND id > 1");
        while(resultSet.next()){
            System.out.println("id : " + resultSet.getInt("id"));
            System.out.println("name : " + resultSet.getString("name"));
            System.out.println("username : " + resultSet.getString("username"));
            System.out.println("status : " + resultSet.getInt("verify"));
        }
    }
    public boolean checkCustomerStatusForUnSuspend(int customerId) throws SQLException {
        boolean canVerify = false;
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT verify FROM customer WHERE id = '" + customerId + "'");
        while(resultSet.next()){
            if(resultSet.getInt("verify") == 2){
                canVerify = true;
            }
        }
        return canVerify;
    }
    public void unSuspendPerson(int customerId) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        PreparedStatement suspendPerson = connection.prepareStatement("UPDATE customer SET verify = 1 WHERE id = ? ");
        suspendPerson.setInt(1,customerId);
        suspendPerson.executeUpdate();
        System.out.println("person successfully unsuspended");
    }
    public boolean checkCustomerCart(int userId) throws SQLException {
        boolean customerHasShopped = false;
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customerbuygoods WHERE customerid = '" + userId + "'");
        while(resultSet.next()){
            customerHasShopped = true;
        }
        return customerHasShopped;
    }
}
