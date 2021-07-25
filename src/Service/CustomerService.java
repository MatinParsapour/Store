package Service;

import entity.Customer;
import repository.CustomerRepository;

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
            while(!backToMainMenu){
                boolean inputMatches = false;
                System.out.println("----- you logged in -----");
                System.out.println("     1.charge account            balance = " + customerRepository.findCurrentBalance(customerRepository.findUserId()));
                System.out.println("     2.see your cart     ");
                System.out.println("     3.add to your cart  ");
                System.out.println("  4.delete from you cart ");
                System.out.println("    5.change password    ");
                System.out.println("    6.change username    ");
                System.out.println("      7.view products    ");
                System.out.println("         8.exit          ");
                System.out.println("-------------------------");
                System.out.print("choose: ");
                int logInChoice = 0;
                while(!inputMatches){
                    try{
                        logInChoice = input.nextInt();
                        while(logInChoice < 1 || logInChoice > 8){
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
                        CustomerService.chargeAccount();
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
                        break;
                    case 8:
                        backToMainMenu = true;
                        break;
                }
            }
        }
    }
    public void signUp() throws SQLException {
        CustomerRepository customerRepository = new CustomerRepository();
        System.out.println("<><><><><><> sign up <><><><><><>");
        Customer.setName(CustomerService.name());
        Customer.setUsername(CustomerService.username());
        Customer.setPassword(CustomerService.password());
        customerRepository.insertCustomer();
        boolean backToMainMenu = false;
        while(!backToMainMenu){
            boolean inputMatches = false;
            System.out.println("----- you logged in -----");
            System.out.println("     1.charge account          balance = " + customerRepository.findCurrentBalance(customerRepository.findUserId()));
            System.out.println("     2.see your cart     ");
            System.out.println("     3.add to your cart  ");
            System.out.println("  4.delete from you cart ");
            System.out.println("    5.change password    ");
            System.out.println("    6.change username    ");
            System.out.println("      7.view products    ");
            System.out.println("         8.exit          ");
            System.out.println("-------------------------");
            System.out.print("choose: ");
            int logInChoice = 0;
            while(!inputMatches){
                try{
                    logInChoice = input.nextInt();
                    while(logInChoice < 1 || logInChoice > 8){
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
                    CustomerService.chargeAccount();
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
                    break;
                case 8:
                    backToMainMenu = true;
                    break;
            }
        }
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
}
