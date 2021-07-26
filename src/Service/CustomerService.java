package Service;

import entity.Customer;
import repository.CustomerRepository;
import repository.ProductRepository;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerService {
    static Scanner input = new Scanner(System.in);
    public void logIn() throws SQLException {
        CustomerRepository customerRepository = new CustomerRepository();
        boolean itDoesntMatch = true;
        boolean backToMainMenu = false;
        System.out.println("<><><><><><> log in <><><><><><>");
        while(!backToMainMenu){
            while(itDoesntMatch){
                try{
                    int choice = 0;
                    boolean logIn = false;
                    while(!logIn){
                        System.out.println("---------- user name -----------");
                        String username = new Scanner(System.in).next();
                        System.out.println("----------- password -----------");
                        String password = new Scanner(System.in).next();
                        logIn = customerRepository.checkUsernameAndPassword(username,password);
                        if(logIn){
                            Customer.setUsername(username);
                            break;
                        }
                        System.out.println("------ username or password is incorrect ------");
                        System.out.println("1.try again                      2.back to home");
                        boolean choiceIsString = true;
                        while(choiceIsString){
                            try{
                                choice = new Scanner(System.in).nextInt();
                                while(choice < 1 || choice > 2){
                                    System.out.println("------ Invalid choice -------");
                                    System.out.print("try again:");
                                    choice = new Scanner(System.in).nextInt();
                                }
                                choiceIsString = false;
                            }catch (InputMismatchException inputException){
                                System.out.println("you should write a number");
                                System.out.print("try again:");
                            }
                        }
                        switch (choice){
                            case 1:
                                break;
                            case 2:
                                logIn =true;
                                itDoesntMatch = false;
                                backToMainMenu = true;
                                break;
                        }
                    }
                    itDoesntMatch = false;
                }catch(InputMismatchException exception){
                    System.out.println("you should write number as a password");
                    System.out.println("try again: ");
                }
            }
            int userId = customerRepository.findUserId();
            boolean userIsVerify = customerRepository.checkVerify(userId);
            if(userIsVerify){
                System.out.println("----- you logged in -----");
                while(!backToMainMenu){
                    boolean inputMatches = false;
                    System.out.println("------------------------");
                    System.out.println("     1.charge account            balance = " + customerRepository.findCurrentBalance(customerRepository.findUserId()));
                    System.out.println("     2.see your cart     ");
                    System.out.println("     3.add to your cart  ");
                    System.out.println("  4.delete from you cart ");
                    System.out.println("    5.change password    ");
                    System.out.println("    6.change username    ");
                    System.out.println("      7.view products    ");
                    System.out.println("      8.final purchase   ");
                    System.out.println("         9.exit          ");
                    System.out.println("-------------------------");
                    System.out.print("choose: ");
                    int logInChoice = 0;
                    while(!inputMatches){
                        try{
                            logInChoice = input.nextInt();
                            while(logInChoice < 1 || logInChoice > 9){
                                System.out.println("----- invalid choice -----");
                                System.out.print("try again : ");
                                logInChoice = input.nextInt();
                            }
                            inputMatches = true;
                        }catch (InputMismatchException exception){
                            System.out.println("you should enter a number");
                            System.out.print("try again: ");
                        }
                    }
                    switch (logInChoice){
                        case 1:
                            chargeAccount();
                            break;
                        case 2:
                            seeYourCart();
                            break;
                        case 3:
                            addToCart();
                            break;
                        case 4:
                            deleteFromCart();
                            break;
                        case 5:
                            changePassword();
                            break;
                        case 6:
                            changeUsername();
                            break;
                        case 7:
                            viewProducts();
                            break;
                        case 8:
                            finalPurchase();
                            break;
                        case 9:
                            backToMainMenu = true;
                            break;
                    }
                }
            }
            else{
                System.out.println("you are not allowed to do anything");
                System.out.println("please wait till admin respond");
                backToMainMenu = true;
            }
        }
    }
    public void signUp() throws SQLException {
        CustomerService customerService = new CustomerService();
        CustomerRepository customerRepository = new CustomerRepository();
        System.out.println("<><><><><><> sign up <><><><><><>");
        Customer.setName(CustomerService.name());
        Customer.setUsername(CustomerService.username());
        Customer.setPassword(CustomerService.password());
        customerRepository.insertCustomer();
        customerService.logIn();
    }
    private static String name(){
        String name = null;
        boolean itMatches = false;
        System.out.println("------ your name ------");
        while(!itMatches){
            try{
                name = input.next();
                itMatches = true;
            }catch (InputMismatchException exception){
                System.out.println("you should write your name by characters");
                System.out.print("try again");
            }
        }
        return name;
    }
    private static String username() throws SQLException {
        CustomerRepository customerRepository = new CustomerRepository();
        String username = null;
        boolean itMatches = false;
        System.out.println("------ username ------");
        while(!itMatches){
            try{
                username = input.next();
                boolean usernameIsOk = customerRepository.checkUsername(username);
                while(usernameIsOk){
                    System.out.println("-------- change your username ---------");
                    System.out.println("---- there is a username like this ----");
                    username = input.next();
                    usernameIsOk = customerRepository.checkUsername(username);
                }
                itMatches = true;
            }catch (InputMismatchException exception){
                System.out.println("you should write your name by characters");
                System.out.print("try again: ");
            }
        }
        return username;
    }
    private static String password(){
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
    private static void chargeAccount() throws SQLException {
        CustomerRepository customerRepository = new CustomerRepository();
        int userId = customerRepository.findUserId();
        System.out.println("----- charge account -----");
        System.out.println("--------- amount ---------");
        int amount = input.nextInt();
        int currentBalance = customerRepository.findCurrentBalance(userId);
        int nextBalance = amount + currentBalance;
        customerRepository.updateUserBalance(nextBalance,userId);
    }
    private static boolean seeYourCart() throws SQLException {
        ProductRepository productRepository = new ProductRepository();
        CustomerRepository customerRepository = new CustomerRepository();
        int userId = customerRepository.findUserId();
        boolean customerHasShoppped = customerRepository.checkCustomerCart(userId);
        if(customerHasShoppped){
            productRepository.findProductId(userId);
            System.out.println("total = " + productRepository.total(userId));
        }
        else{
            System.out.println("you haven't add anything to your cart");
        }
        return customerHasShoppped;
    }
    private static void addToCart() throws SQLException {
        boolean backToMainMenu = false;
        ProductRepository productRepository = new ProductRepository();
        productRepository.findProducts();
        while(!backToMainMenu){
            int numberOfProducts = productRepository.checkNumberOfProducts();
            if(numberOfProducts >= 5){
                int cartFullChoice = 0;
                System.out.println("----- you can't buy product more than 5 -----");
                System.out.println("1.back to menu             2.delete from cart");
                boolean inputMatch = false;
                while(!inputMatch){
                    try{
                        cartFullChoice = new Scanner(System.in).nextInt();
                        while(cartFullChoice < 1 || cartFullChoice > 2){
                            System.out.println("----- invalid choice -----");
                            System.out.println("try again : ");
                            cartFullChoice = new Scanner(System.in).nextInt();
                        }
                        inputMatch = true;
                    }catch (InputMismatchException exception){
                        System.out.println("you should enter a number");
                        System.out.println("try again: ");
                    }
                }
                if (cartFullChoice != 1) {
                    deleteFromCart();
                }
                break;
            }
            boolean inputIsCorrect = false;
            while(!inputIsCorrect){
                try{
                    System.out.println("1.enter product id           2.back to menu");
                    int choice = input.nextInt();
                    while(choice < 1 || choice > 2){
                        System.out.println("----- invalid choice -----");
                        System.out.print("try again: ");
                        choice = input.nextInt();
                    }
                    if(choice == 1){
                        System.out.print("product id: ");
                        int productId = input.nextInt();
                        int inventory = productRepository.checkInventory(productId);
                        if(inventory > 0){
                            productRepository.addToCart(productId);
                            inputIsCorrect = true;
                        }
                        else{
                            System.out.println("sorry we are out of this product");
                        }
                    }else{
                        inputIsCorrect = true;
                        backToMainMenu = true;
                    }
                }catch (InputMismatchException exception){
                    System.out.println("you should write a number");
                    System.out.print("try again: ");
                }
            }
        }
    }
    private static void deleteFromCart() throws SQLException {
        ProductRepository productRepository = new ProductRepository();
        CustomerRepository customerRepository = new CustomerRepository();
        int userId = customerRepository.findUserId();
        boolean customerHasShoppped = customerRepository.checkCustomerCart(userId);
        if(customerHasShoppped){
            boolean backToMainMenu = false;
            while(!backToMainMenu){
                customerHasShoppped = customerRepository.checkCustomerCart(userId);
                if(!customerHasShoppped){
                    break;
                }
                productRepository.findProductId(userId);
                boolean inputMatch = false;
                int choice = 0;
                System.out.println("   1.delete from cart   ");
                System.out.println("   2.back to main menu  ");
                System.out.print(":");
                while(!inputMatch){
                    try{
                        choice = new Scanner(System.in).nextInt();
                        while(choice < 1 || choice > 2){
                            System.out.println("----- invalid choice -----");
                            System.out.println("try again: ");
                            choice = new Scanner(System.in).nextInt();
                        }
                        inputMatch = true;
                    }catch (InputMismatchException exception){
                        System.out.println("you should enter a number");
                        System.out.print("try again: ");
                    }
                }
                if(choice == 1){
                    System.out.println("----- product id -----");
                    int productId = input.nextInt();
                    boolean idIsCorrect = productRepository.checkProductId(productId);
                    while(!idIsCorrect){
                        System.out.println("----------- invalid id -----------");
                        System.out.println("this id isn't one of your products");
                        System.out.print("try again: ");
                        productId = input.nextInt();
                        idIsCorrect = productRepository.checkProductId(productId);
                    }
                    productRepository.deleteProduct(productId);
                }else{
                    break;
                }
            }
        }else{
            System.out.println("you haven't add anything to your cart");
        }
    }
    private static void changePassword() throws SQLException {
        CustomerRepository customerRepository = new CustomerRepository();
        customerRepository.findPassword();
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
            System.out.println("----- new password -----");
            int password = Integer.parseInt(password());
            customerRepository.changePassword(password);
        }else{
            System.out.println("you password didn't change");
        }
    }
    private static void changeUsername() throws SQLException {
        CustomerRepository customerRepository = new CustomerRepository();
        System.out.println("your username is : " + Customer.getUsername());
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
            String username = input.next();
            boolean usernameAccepted = customerRepository.checkUsername(username);
            while(usernameAccepted){
                System.out.println("----- invalid username ------");
                System.out.println("there is a username like this");
                System.out.print("try again: ");
                username = input.next();
                usernameAccepted = customerRepository.checkUsername(username);
            }
            int userId = customerRepository.findUserId();
            Customer.setUsername(username);
            customerRepository.changeUsername(userId);
        }else{
            System.out.println("you username didn't change");
        }
    }
    private static void viewProducts() throws SQLException {
        ProductRepository productRepository = new ProductRepository();
        System.out.println("------ view products ------");
        productRepository.findProducts();
    }
    private static void finalPurchase() throws SQLException {
        CustomerRepository customerRepository = new CustomerRepository();
        ProductRepository productRepository = new ProductRepository();
        int userId = customerRepository.findUserId();
        int balance = customerRepository.findCurrentBalance(userId);
        int total = productRepository.total(userId);
        System.out.println("total = " + total);
        System.out.println("do you want to purchase?");
        System.out.println("1.Yes               2.NO");
        System.out.println(":");
        boolean inputMatch = false;
        int choice = 0;
        while(!inputMatch){
            try{
                choice = new Scanner(System.in).nextInt();
                while(choice < 1 || choice > 2){
                    System.out.println("you should choose between menu options");
                    System.out.println("try again: ");
                    choice = new Scanner(System.in).nextInt();
                }
                inputMatch = true;
            }catch (InputMismatchException exception){
                System.out.println("you should enter a number");
                System.out.print("try again: ");
            }
        }
        if(choice == 1){
            boolean checkCart = customerRepository.checkCart(userId);
            if(checkCart){
                if(balance > total){
                    int nextBalance = balance - total;
                    customerRepository.updateUserBalance(nextBalance,userId);
                    customerRepository.clearCart(userId);
                }else{
                    System.out.println("your balance is not enough");
                    System.out.println("     1.charge account     ");
                    System.out.println("    2.delete from cart    ");
                    System.out.println("     3.back to menu       ");
                    boolean inputMatches = false;
                    int yourChoice = 0;
                    while(!inputMatches){
                        try{
                            yourChoice = new Scanner(System.in).nextInt();
                            while(choice < 1 || choice > 2){
                                System.out.println("----- invalid choice -----");
                                System.out.print("try again: ");
                                yourChoice = new Scanner(System.in).nextInt();
                            }
                            inputMatches = true;
                        }catch (InputMismatchException exception){
                            System.out.println("you should enter a number");
                            System.out.print("try again: ");
                        }
                    }
                    switch (yourChoice){
                        case 1:
                            chargeAccount();
                            break;
                        case 2:
                            deleteFromCart();
                            break;
                        case 3:
                            break;
                    }
                }
            }
            else{
                System.out.println("you haven't add anything to your cart");
            }
        }
    }
}
