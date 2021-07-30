package Service;

import repository.AdminRepository;
import repository.CustomerRepository;
import repository.ProductRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Store {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "Mm1234!@#$");
        AdminRepository adminRepository = new AdminRepository(connection);
        CustomerRepository customerRepository = new CustomerRepository(connection);
        ProductRepository productRepository = new ProductRepository(connection,customerRepository);

        ProductService productService = new ProductService(productRepository);
        AdminService adminService = new AdminService(adminRepository,customerRepository,productRepository,productService);
        CustomerService  customerService = new CustomerService(customerRepository,productRepository);


        boolean exit = false;
        CreateTable createTable = new CreateTable();
        createTable.customerTable();
        createTable.goodsTable();
        createTable.customerBuyGoods();
        System.out.println("------ welcome to store ------");
        while(!exit){
            boolean inputIsCorrect = false;
            System.out.println("           1.Admin            ");
            System.out.println("           2.Log in           ");
            System.out.println("           3.Sign up          ");
            System.out.println("           4.View goods       ");
            System.out.println("           5.Exit             ");
            System.out.println("------------------------------");
            System.out.print("choose : ");
            int choice = 0;
            while(!inputIsCorrect){
                try{
                    choice = new Scanner(System.in).nextInt();
                    while(choice < 1 || choice > 5){
                        System.out.println("------ Invalid choice -------");
                        System.out.println(" choose between menu options ");
                        choice = new Scanner(System.in).nextInt();
                    }
                    inputIsCorrect = true;
                }catch (InputMismatchException exception){
                    System.out.println("you should enter a number");
                    System.out.print("try again: ");
                }
            }
            switch (choice){
                case 1:
                    adminService.adminLogIn();
                    break;
                case 2:
                    customerService.logIn();
                    break;
                case 3:
                    customerService.signUp();
                    break;
                case 4:
                    productRepository.findProducts();
                    break;
                case 5:
                    System.out.println("hope to see you soon");
                    exit = true;
                    break;
            }
        }
    }
}
