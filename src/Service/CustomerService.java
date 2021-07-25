package Service;

import repository.CustomerRepository;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

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
                        int password = new Scanner(System.in).nextInt();
                        logIn = customerRepository.checkUsernameAndPassword(username,String.valueOf(password));
                        if(logIn){
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
            if(!backToMainMenu){
                System.out.println("show menu");
                backToMainMenu = true;
            }
        }

    }
}
