package Service;

import entity.Goods;
import repository.ProductRepository;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductService {
    public void productInformation() throws SQLException {
        ProductRepository productRepository = new ProductRepository();
        boolean inputIsCorrect = false;
        while (!inputIsCorrect){
            try{
                String productName = productName();
                String category = productCategories();
                String subcategory = subCategory();
                int cost = 0;
                int number = 0;
                while(!inputIsCorrect){
                    try{
                        cost = cost();
                        number = number();
                        inputIsCorrect = true;
                    }catch (InputMismatchException exception){
                        System.out.println("you should enter a number");
                        System.out.print("try again: ");
                    }
                }
                Goods.setName(productName);
                Goods.setCategory(category);
                Goods.setSubcategory(subcategory);
                Goods.setCost(cost);
                Goods.setNumber(number);
                productRepository.insertToGoods();
                inputIsCorrect = true;
            }catch (InputMismatchException exception){
                System.out.println("you should enter character");
                System.out.println("try again: ");
            }
        }
    }
    private  static String productName(){
        String name = null;
        boolean inputMatch = false;
        while(!inputMatch){
            try{
                System.out.println("----- product name -----");
                name = new Scanner(System.in).nextLine();
                System.out.println("is this name an acceptable name?");
                System.out.println("1.yes                       2.no");
                int accept = new Scanner(System.in).nextInt();
                while(accept < 1 || accept > 2){
                    System.out.println("you should choose between menu options");
                    System.out.println("try again: ");
                    accept = new Scanner(System.in).nextInt();
                }
                if(accept == 1){
                    break;
                }
            }catch (InputMismatchException exception){
                System.out.println("you should enter number");
                System.out.print("try again:");
            }
        }
        return  name;
    }
    private static String productCategories() throws SQLException {
        ProductRepository productRepository = new ProductRepository();
        productRepository.productCategory();
        boolean inputMatch = false;
        String category = null;
        while(!inputMatch){
            try{
                System.out.println("----- category name -----");
                category = new Scanner(System.in).nextLine();
                boolean categoryExists = productRepository.checkCategory(category);
                while(!categoryExists){
                    try{
                        System.out.println("this category doesn't exists");
                        System.out.println("do you want to add?");
                        System.out.println("1.yes          2.no");
                        int addCategory  = new Scanner(System.in).nextInt();
                        while(addCategory < 1 || addCategory > 2){
                            System.out.println("you should choose from menu options");
                            System.out.print("try again : ");
                            addCategory = new Scanner(System.in).nextInt();
                        }
                        if(addCategory == 1){
                            inputMatch = true;
                            break;
                        }
                        else{
                            break;
                        }
                    }catch (InputMismatchException exception){
                        System.out.println("you should enter number");
                        System.out.print("try again : ");
                    }
                }
            }catch (InputMismatchException exception){
                System.out.println("you should enter character");
                System.out.print("try again: ");
            }
        }
        return category;
    }
    private static String subCategory(){
        String subCategory = null;
        boolean inputMatch = false;
        while(!inputMatch){
            try{
                System.out.println("----- product name -----");
                subCategory = new Scanner(System.in).nextLine();
                System.out.println("is this name an acceptable name?");
                System.out.println("1.yes                       2.no");
                int accept = new Scanner(System.in).nextInt();
                while(accept < 1 || accept > 2){
                    System.out.println("you should choose between menu options");
                    System.out.println("try again: ");
                    accept = new Scanner(System.in).nextInt();
                }
                if(accept == 1){
                    break;
                }
            }catch (InputMismatchException exception){
                System.out.println("you should enter number");
                System.out.print("try again:");
            }
        }
        return  subCategory;
    }
    private static int cost(){
        int Cost = 0;
        boolean inputMatch = false;
        while(!inputMatch){
            try{
                System.out.println("----- product name -----");
                Cost = new Scanner(System.in).nextInt();
                System.out.println("is this name an acceptable name?");
                System.out.println("1.yes                       2.no");
                int accept = new Scanner(System.in).nextInt();
                while(accept < 1 || accept > 2){
                    System.out.println("you should choose between menu options");
                    System.out.println("try again: ");
                    accept = new Scanner(System.in).nextInt();
                }
                if(accept == 1){
                    break;
                }
            }catch (InputMismatchException exception){
                System.out.println("you should enter number");
                System.out.print("try again:");
            }
        }
        return  Cost;
    }
    private static int number(){
        int number = 0;
        boolean inputMatch = false;
        while(!inputMatch){
            try{
                System.out.println("----- product name -----");
                number = new Scanner(System.in).nextInt();
                System.out.println("is this name an acceptable name?");
                System.out.println("1.yes                       2.no");
                int accept = new Scanner(System.in).nextInt();
                while(accept < 1 || accept > 2){
                    System.out.println("you should choose between menu options");
                    System.out.println("try again: ");
                    accept = new Scanner(System.in).nextInt();
                }
                if(accept == 1){
                    break;
                }
            }catch (InputMismatchException exception){
                System.out.println("you should enter number");
                System.out.print("try again:");
            }
        }
        return  number;
    }
}
