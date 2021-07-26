package Service;

import entity.Admin;
import repository.AdminRepository;
import repository.CustomerRepository;
import repository.ProductRepository;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminService {
    public void adminLogIn() throws SQLException {
        AdminRepository adminRepository = new AdminRepository();
        boolean backToMainMenu = false;
        while(!backToMainMenu){
            try{
                System.out.println("------ log in admin ------");
                System.out.println("-------- username --------");
                String username = new Scanner(System.in).next();
                System.out.println("-------- password --------");
                String password = new Scanner(System.in).next();
                boolean heIsAdmin = adminRepository.checkAdmin(username,password);
                if(heIsAdmin){
                    Admin.setUsername(username);
                    adminMenu();
                    break;
                }
                while(!heIsAdmin){
                    int logInFailed = 0;
                    System.out.println("❌❌❌❌❌ you are not admin ❌v❌❌❌");
                    System.out.println("1.try again     2.back to main menu");
                    boolean inputMatches = false;
                    while(!inputMatches){
                        try{
                            logInFailed = new Scanner(System.in).nextInt();
                            while(logInFailed < 1 || logInFailed > 2){
                                System.out.println("you should choose between menu options");
                                System.out.print("try again :");
                                logInFailed = new Scanner(System.in).nextInt();
                            }
                            inputMatches = true;
                        }catch (InputMismatchException exception){
                            System.out.println("you should write number");
                            System.out.print("try again: ");
                        }
                    }
                    if(logInFailed == 1){
                        break;
                    }
                    else{
                        backToMainMenu = true;
                        break;
                    }
                }
            }catch (InputMismatchException exception){
                System.out.println("you should write character");
                System.out.println("try again: ");
            }
        }
    }
    private static void adminMenu() throws SQLException {
        System.out.println("<><><><><> welcome <><><><><>");
        boolean mainMenu = false;
        while(!mainMenu){
            int adminChoice = 0;
            boolean inputIsIncorrect = true;
            while(inputIsIncorrect){
                try{
                    System.out.println("----------------------------------");
                    System.out.println("          1.add a product         ");
                    System.out.println("         2.delete a product       ");
                    System.out.println("  3.increase number of a product  ");
                    System.out.println("         4. verify a person       ");
                    System.out.println("         5.suspend a person       ");
                    System.out.println("        6.unsuspend a person      ");
                    System.out.println("             7.exit               ");
                    System.out.println("----------------------------------");
                    System.out.print("choose : ");
                    adminChoice = new Scanner(System.in).nextInt();
                    while(adminChoice < 1 || adminChoice > 7){
                        System.out.println("you should choose between menu options");
                        System.out.print("try again:");
                        adminChoice = new Scanner(System.in).nextInt();
                    }
                    inputIsIncorrect = false;
                }catch (InputMismatchException exception){
                    System.out.println("you should enter a number");
                    System.out.print("try again : ");
                }
            }
            switch (adminChoice){
                case 1:
                    addProduct();
                    break;
                case 2:
                    deleteProduct();
                    break;
                case 3:
                    increaseInventory();
                    break;
                case 4:
                    verifyPerson();
                    break;
                case 5:
                    suspendPerson();
                    break;
                case 6:
                    break;
                case 7:
                    mainMenu = true;
                    break;
            }
        }
    }
    private static void addProduct() throws SQLException {
        ProductService productService = new ProductService();
        ProductRepository productRepository = new ProductRepository();
        productRepository.findProducts();
        while(true){
            try{
                System.out.println("do you want to add a product? 1.Yes    2.No");
                int addChoice = new Scanner(System.in).nextInt();
                while(addChoice < 1 || addChoice > 2){
                    System.out.println("you should choose between menu options");
                    System.out.print("try again: ");
                    addChoice = new Scanner(System.in).nextInt();
                }
                if(addChoice == 1){
                    productService.productInformation();
                }else{
                    break;
                }
            }catch (InputMismatchException exception){
                System.out.println("you should enter a number");
                System.out.print("try again: ");
            }

        }
    }
    private static void deleteProduct() throws SQLException {
        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService();
        productRepository.findProducts();
        while(true){
            try{
                System.out.println("do you want to delete a product? 1.yes   2.no");
                int deleteProduct = new Scanner(System.in).nextInt();
                while(deleteProduct < 1 || deleteProduct > 2){
                    System.out.println("you should choose between menu options");
                    System.out.print("try again : ");
                    deleteProduct = new Scanner(System.in).nextInt();
                }
                if(deleteProduct == 1){
                    productService.deleteProduct();
                }else{
                    break;
                }
            }catch (InputMismatchException exception){
                System.out.println("you should enter a number");
                System.out.print("try again: ");
            }
        }
    }
    private static void increaseInventory() throws SQLException {
        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService();
        productRepository.findProducts();
        while(true){
            try{
                System.out.println("do you want to increase inventory? 1.yes  2.no");
                int choice = new Scanner(System.in).nextInt();
                while(choice < 1 || choice > 2){
                    System.out.println("you should choose between menu options");
                    System.out.print("try again: ");
                    choice = new Scanner(System.in).nextInt();
                }
                if(choice == 1){
                    productService.increaseInventory();
                }else{
                    break;
                }
            }catch (InputMismatchException exception){
                System.out.println("you should enter a number");
                System.out.println("try again: ");
            }
        }
    }
    private static void verifyPerson() throws SQLException {
        CustomerRepository customerRepository = new CustomerRepository();
        customerRepository.findUnverifiedPeople();
        while(true){
            try{
                System.out.println("do you want to verify a person? 1.yes  2.no");
                int choice = new Scanner(System.in).nextInt();
                while (choice < 1 || choice > 2){
                    System.out.println("you should choose between menu options");
                    System.out.print("try again: ");
                    choice = new Scanner(System.in).nextInt();
                }
                if(choice == 1){
                    System.out.println("------ customer id ------");
                    int customerId = new Scanner(System.in).nextInt();
                    boolean canVerify = customerRepository.checkCustomerStatusForVerify(customerId);
                    if(canVerify){
                        customerRepository.verifyPerson(customerId);
                    }else{
                        System.out.println("this id is not available to verify");
                        System.out.println("1.try again         2.back to menu");
                        int idIsIncorrect = new Scanner(System.in).nextInt();
                        while (idIsIncorrect < 1 || idIsIncorrect > 2){
                            System.out.println("you should choose between menu options");
                            System.out.print("try again : ");
                            idIsIncorrect = new Scanner(System.in).nextInt();
                        }
                        if(idIsIncorrect == 2){
                            break;
                        }
                    }
                }else{
                    break;
                }
            }catch (InputMismatchException exception){
                System.out.println("you should enter a number");
                System.out.println("try again : ");
            }
        }
    }
    private static void suspendPerson() throws SQLException {
        CustomerRepository customerRepository = new CustomerRepository();
        customerRepository.findVerifiedPeople();
        while(true){
            try{
                System.out.println("do you want to verify a person? 1.yes  2.no");
                int choice = new Scanner(System.in).nextInt();
                while (choice < 1 || choice > 2){
                    System.out.println("you should choose between menu options");
                    System.out.print("try again: ");
                    choice = new Scanner(System.in).nextInt();
                }
                if(choice == 1){
                    System.out.println("------ customer id ------");
                    int customerId = new Scanner(System.in).nextInt();
                    boolean canVerify = customerRepository.checkCustomerStatusForSuspend(customerId);
                    if(canVerify){
                        customerRepository.suspendPerson(customerId);
                    }else{
                        System.out.println("this id is not available to verify");
                        System.out.println("1.try again         2.back to menu");
                        int idIsIncorrect = new Scanner(System.in).nextInt();
                        while (idIsIncorrect < 1 || idIsIncorrect > 2){
                            System.out.println("you should choose between menu options");
                            System.out.print("try again : ");
                            idIsIncorrect = new Scanner(System.in).nextInt();
                        }
                        if(idIsIncorrect == 2){
                            break;
                        }
                    }
                }else{
                    break;
                }
            }catch (InputMismatchException exception){
                System.out.println("you should enter a number");
                System.out.println("try again : ");
            }
        }
    }
}
