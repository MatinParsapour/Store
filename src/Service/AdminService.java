package Service;

import entity.Admin;
import entity.Goods;
import repository.AdminRepository;
import repository.ProductRepository;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class AdminService {
    static Scanner input = new Scanner(System.in);
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
        boolean mainMenu = false;
        while(!mainMenu){
            System.out.println("<><><><><> welcome <><><><><>");
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
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
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

        int addChoice = 0;
        while(true){
            System.out.println("do you want to add a product? 1.Yes    2.No");
            boolean inputMatch = false;
            while(!inputMatch){
                try{
                    addChoice = new Scanner(System.in).nextInt();
                    while(addChoice < 1 || addChoice > 2){
                        System.out.println("you should choose between menu options");
                        System.out.print("try again: ");
                        addChoice = new Scanner(System.in).nextInt();
                    }
                    inputMatch = true;
                }catch (InputMismatchException exception){
                    System.out.println("you should enter a number");
                    System.out.print("try again: ");
                }
            }
            if(addChoice == 1){
                productService.productInformation();
            }else{
                break;
            }
        }
    }
}
