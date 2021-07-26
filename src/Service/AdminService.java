package Service;

import repository.AdminRepository;

import java.sql.SQLException;
import java.util.InputMismatchException;
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
            if(!backToMainMenu){
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
                        System.out.println("----------------------------------");
                        System.out.println("choose : ");
                        adminChoice = new Scanner(System.in).nextInt();
                        while(adminChoice < 1 || adminChoice > 6){
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
                        backToMainMenu = true;
                        break;
                }
            }
        }
    }
}
