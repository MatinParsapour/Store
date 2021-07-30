package Service;

import entity.Admin;
import entity.Customer;
import repository.AdminRepository;
import repository.CustomerRepository;
import repository.ProductRepository;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminService {

    private AdminRepository adminRepository;
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;
    private ProductService productService;

    public AdminService(AdminRepository adminRepository,CustomerRepository customerRepository,ProductRepository productRepository,ProductService productService){
        this.adminRepository = adminRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.productService = productService;
    }

    public void adminLogIn() throws SQLException {
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
    private void adminMenu() throws SQLException {
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
                    System.out.println("  4.decrease number of a product  ");
                    System.out.println("         5. verify a person       ");
                    System.out.println("         6.suspend a person       ");
                    System.out.println("        7.unsuspend a person      ");
                    System.out.println("         8.change password        ");
                    System.out.println("         9.change username        ");
                    System.out.println("             10.exit               ");
                    System.out.println("----------------------------------");
                    System.out.print("choose : ");
                    adminChoice = new Scanner(System.in).nextInt();
                    while(adminChoice < 1 || adminChoice > 10){
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
                    decreaseInvenroy();
                    break;
                case 5:
                    verifyPerson();
                    break;
                case 6:
                    suspendPerson();
                    break;
                case 7:
                    unSuspendPerson();
                    break;
                case 8:
                    changePassword();
                    break;
                case 9:
                    changeUsername();
                    break;
                case 10:
                    mainMenu = true;
                    break;
            }
        }
    }
    private void addProduct() throws SQLException {
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
    private void deleteProduct() throws SQLException {
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
    private void increaseInventory() throws SQLException {
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
    private void decreaseInvenroy() throws SQLException {
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
                    productService.decreaseInventroy();
                }else{
                    break;
                }
            }catch (InputMismatchException exception){
                System.out.println("you should enter a number");
                System.out.println("try again: ");
            }
        }
    }
    private void verifyPerson() throws SQLException {
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
    private void suspendPerson() throws SQLException {
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
                    boolean canSuspend = customerRepository.checkCustomerStatusForSuspend(customerId);
                    if(canSuspend){
                        customerRepository.suspendPerson(customerId);
                    }else{
                        System.out.println("this id is not available to suspend");
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
    private void unSuspendPerson() throws SQLException {
        customerRepository.findSuspendedPeople();
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
                    boolean canUnSuspend = customerRepository.checkCustomerStatusForUnSuspend(customerId);
                    if(canUnSuspend){
                        customerRepository.unSuspendPerson(customerId);
                    }else{
                        System.out.println("this id is not available to unsuspend");
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
    private void changePassword() throws SQLException {
        adminRepository.findAdminPassword();
        boolean inputMatch = false;
        int choice = 0;
        while(!inputMatch){
            try{
                System.out.println("1.change password        2.back to menu");
                System.out.print(":");
                choice = new Scanner(System.in).nextInt();
                while(choice < 1 || choice > 2){
                    System.out.println("----- invalid choice -----");
                    System.out.print("try again: ");
                    choice = new Scanner(System.in).nextInt();
                }
                inputMatch = true;
            }catch (InputMismatchException exception){
                System.out.println("you should enter a number");
                System.out.println("try again: ");
            }
        }
        if(choice == 1){
            String password = password();
            adminRepository.changeAdminPassword(password);
        }else{
            System.out.println("you password didn't change");
        }
    }
    private String password(){
        Scanner input = new Scanner(System.in);
        String validPassword = "[0-9]{10}";
        Pattern pattern = Pattern.compile(validPassword);
        System.out.println("------ password ------");
        String password = input.next();
        Matcher matcher = pattern.matcher(password);
        while(!matcher.matches()){
            System.out.println("you should write a 10-digit password");
            System.out.print("try again: ");
            password = input.next();
            matcher = pattern.matcher(password);
        }
        return password;
    }
    private void changeUsername() throws SQLException {
        adminRepository.findAdminUsername();
        boolean inputMatch = false;
        int choice = 0;
        while(!inputMatch){
            try{
                System.out.println("1.change username        2.back to menu");
                System.out.print(":");
                choice = new Scanner(System.in).nextInt();
                while(choice < 1 || choice > 2){
                    System.out.println("----- invalid choice -----");
                    System.out.print("try again: ");
                    choice = new Scanner(System.in).nextInt();
                }
                inputMatch = true;
            }catch (InputMismatchException exception){
                System.out.println("you should enter a number");
                System.out.println("try again: ");
            }
        }
        if(choice == 1){
            System.out.println("----- new username -----");
            String username = new Scanner(System.in).next();
            boolean usernameAccepted = customerRepository.checkUsername(username);
            while(usernameAccepted){
                System.out.println("----- invalid username ------");
                System.out.println("there is a username like this");
                System.out.print("try again: ");
                username = new Scanner(System.in).next();
                usernameAccepted = customerRepository.checkUsername(username);
            }
            adminRepository.changeAdminUsername(username);
        }else{
            System.out.println("you username didn't change");
        }
    }
}
