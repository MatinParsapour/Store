package repository;

import java.sql.*;

public class TableRepository {
    public void  customerTable() throws SQLException {
        Connection connection = DriverManager.getConnection("\"jdbc:mysql://localhost:3306/website\", \"root\", \"Mm1234!@#$\"");
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS `store`.`new_table` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `username` VARCHAR(100) NOT NULL,\n" +
                "  `password` VARCHAR(11) NOT NULL,\n" +
                "  `balance` INT NOT NULL DEFAULT 0,\n" +
                "  PRIMARY KEY (`id`));");
    }
}
