package Service;

import repository.TableRepository;

import java.sql.SQLException;

public class Store {
    public static void main(String[] args) throws SQLException {
        TableRepository tableRepository = new TableRepository();
        tableRepository.customerTable();
        tableRepository.goodsTable();
        tableRepository.customerBuyGoods();
    }
}
