package Service;

import java.util.Scanner;

public class Admin {
    static Scanner input = new Scanner(System.in);
    public void adminLogIn(){
        System.out.println("------ log in admin ------");
        System.out.println("-------- username --------");
        String username = input.next();
        System.out.println("-------- password --------");
        String password = input.next();

    }
}
