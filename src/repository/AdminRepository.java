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
}
