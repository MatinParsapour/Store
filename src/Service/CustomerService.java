package Service;

import java.util.Scanner;

public class CustomerService {
    static Scanner input = new Scanner(System.in);
    public void logIn(){
        System.out.println("<><><><><><> log in <><><><><><>");
        System.out.println("---------- user name -----------");
        String username = input.next();
        System.out.println("----------- password -----------");
        int password = input.nextInt();
    }
}
