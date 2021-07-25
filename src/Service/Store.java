package Service;

import repository.TableRepository;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Store {
    public static void main(String[] args) throws SQLException {
        boolean exit = false;
        TableRepository tableRepository = new TableRepository();
        tableRepository.customerTable();
        tableRepository.goodsTable();
        tableRepository.customerBuyGoods();
        System.out.println("------ welcome to store ------");
        while(!exit){
            boolean inputIsCorrect = false;
            System.out.println("           1.Log in           ");
            System.out.println("           2.Sign up          ");
            System.out.println("           3.View goods       ");
            System.out.println("           4.Exit             ");
            System.out.println("------------------------------");
            System.out.print("choose : ");
            int choice = 0;
            while(!inputIsCorrect){
                try{
                    choice = new Scanner(System.in).nextInt();
                    while(choice < 1 || choice > 4){
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
                    System.out.println("you logged in");
                    break;
                case 2:
                    System.out.println("you signed up");
                    break;
                case 3:
                    System.out.println("you see goods");
                    break;
                case 4:
                    System.out.println("hope to see you soon");
                    exit = true;
                    break;
            }
        }
    }
}
