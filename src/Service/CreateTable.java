package Service;

import java.sql.*;

public class CreateTable {
    public void  customerTable() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE `store`.`customer` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `username` VARCHAR(100) NOT NULL,\n" +
                "  `password` VARCHAR(11) NOT NULL,\n" +
                "  `balance` INT NOT NULL DEFAULT 0,\n" +
                "  `verify` INT NOT NULL DEFAULT 0,\n" +
                "  PRIMARY KEY (`id`));\n");
    }
    public void goodsTable()throws SQLException{
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS `store`.`goods` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(100) NOT NULL,\n" +
                "  `category` VARCHAR(45) NOT NULL,\n" +
                "  `subcategory` VARCHAR(45) NOT NULL,\n" +
                "  `cost` INT NOT NULL,\n" +
                "  `number` INT NOT NULL DEFAULT 20,\n" +
                "  PRIMARY KEY (`id`));\n");
    }
    public void customerBuyGoods()throws SQLException{
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        Statement statement = connection.createStatement();
        statement.execute("CREATE TABLE IF NOT EXISTS `store`.`customerbuygoods` (\n" +
                "  `customerid` INT NOT NULL,\n" +
                "  `goodsid` INT NOT NULL,\n" +
                "  INDEX `id_goods_idx` (`goodsid` ASC) VISIBLE,\n" +
                "  CONSTRAINT `id_customer`\n" +
                "    FOREIGN KEY (`customerid`)\n" +
                "    REFERENCES `store`.`customer` (`id`)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION,\n" +
                "  CONSTRAINT `id_goods`\n" +
                "    FOREIGN KEY (`goodsid`)\n" +
                "    REFERENCES `store`.`goods` (`id`)\n" +
                "    ON DELETE NO ACTION\n" +
                "    ON UPDATE NO ACTION);\n");
    }
}
