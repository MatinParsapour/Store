package repository;

import java.sql.*;

public class AdminRepository {
    public boolean checkAdmin(String username, String password) throws SQLException {
        boolean heIsAdmin = false;
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customer WHERE id = 1");
        while(resultSet.next()){
            if(resultSet.getString("username").equals(username) && resultSet.getString("password").equals(password)){
                heIsAdmin = true;
            }
        }
        return heIsAdmin;
    }
    public void findAdminPassword() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customer WHERE id = 1 ");
        while(resultSet.next()){
            System.out.println("your password : " + resultSet.getInt("password"));
        }
    }
    public void changeAdminPassword(String password) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        PreparedStatement changePassword = connection.prepareStatement("UPDATE customer SET password = ? WHERE id = 1");
        changePassword.setString(1,password);
        changePassword.executeUpdate();
        System.out.println("your password successfully changed");
    }
    public void findAdminUsername() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customer WHERE id = 1 ");
        while(resultSet.next()){
            System.out.println("your username : " + resultSet.getString("username"));
        }
    }
    public void changeAdminUsername(String username) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        PreparedStatement changeUsername = connection.prepareStatement("UPDATE customer SET username = ? WHERE id = 1");
        changeUsername.setString(1,username);
        changeUsername.executeUpdate();
        System.out.println("your username successfully changed");
    }
}
