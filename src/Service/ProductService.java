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
                inputMatch = true;
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
                System.out.println("----- subcategory name -----");
                subCategory = new Scanner(System.in).nextLine();
                System.out.println("is this name an acceptable subcategory name?");
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
                System.out.println("----- cost -----");
                Cost = new Scanner(System.in).nextInt();
                System.out.println("is this name an acceptable cost?");
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
                System.out.println("----- number -----");
                number = new Scanner(System.in).nextInt();
                System.out.println("is this name an acceptable number?");
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
    public void deleteProduct () throws SQLException {
        ProductRepository productRepository = new ProductRepository();
        boolean productDeleted = false;
        while(!productDeleted){
            try{
                System.out.println("------ product id ------");
                int productId = new Scanner(System.in).nextInt();
                System.out.println("this id is ok? 1.yes 2. no");
                int choice = new Scanner(System.in).nextInt();
                while(choice < 1 || choice > 2){
                    System.out.println("you should choose between menu options");
                    System.out.println("try again: ");
                    choice = new Scanner(System.in).nextInt();
                }
                if(choice == 1){
                    boolean idIsCorrect = productRepository.checkgoodsId(productId);
                    if(idIsCorrect){
                        productRepository.deleteGoods(productId);
                        break;
                    }else{
                        System.out.println("this is incorrect");
                        System.out.println("   1.try again   ");
                        System.out.println(" 2.back to menu  ");
                        System.out.println("-----------------");
                        System.out.print(":");
                        int incorrectId = new Scanner(System.in).nextInt();
                        while(incorrectId < 1 || incorrectId > 2){
                            System.out.println("you should choose between menu options");
                            System.out.print("try again: ");
                            incorrectId = new Scanner(System.in).nextInt();
                        }
                        switch (incorrectId){
                            case 1:
                                break;
                            case 2:
                                productDeleted = true;
                                break;
                        }
                    }
                }else{
                    System.out.println("1.try again   2.back to menu");
                    int select = new Scanner(System.in).nextInt();
                    while(select < 1 || select > 2){
                        System.out.println("you should choose between menu options");
                        System.out.print("try again: ");
                        select = new Scanner(System.in).nextInt();
                    }
                    switch (select){
                        case 1:
                            break;
                        case 2:
                            productDeleted = true;
                            break;
                    }
                }
            }catch (InputMismatchException exception){
                System.out.println("you should enter a number");
                System.out.println("try again : ");
            }
        }
    }
    public void increaseInventory() throws SQLException {
        ProductRepository productRepository = new ProductRepository();
        while(true){
            try{
                System.out.println("----- product id -----");
                int productId = new Scanner(System.in).nextInt();
                boolean idIsOk = productRepository.checkgoodsId(productId);
                if(idIsOk){
                    int numberOfProduct = productRepository.findNumberOfProduct(productId);
                    System.out.println("number : " + numberOfProduct);
                    System.out.println("----- amount -----");
                    System.out.println("This number will be added to the current inventory");
                    int amount = new Scanner(System.in).nextInt();
                    int inventory = numberOfProduct + amount;
                    productRepository.updateInventory(inventory,productId);
                    break;
                }else{
                    System.out.println("this product isn't in the inventory");
                    System.out.println("1.try again          2.back to menu");
                    int choice = new Scanner(System.in).nextInt();
                    while(choice < 1 || choice > 2){
                        System.out.println("you should choose between menu options");
                        System.out.print("try again: ");
                        choice = new Scanner(System.in).nextInt();
                    }
                    if(choice == 2){
                        break;
                    }
                }
            }catch (InputMismatchException exception){
                System.out.println("you should enter a number");
                System.out.println("try again: ");
            }
        }
    }
    public void decreaseInventroy() throws SQLException {
        ProductRepository productRepository = new ProductRepository();
        while(true){
            try{
                System.out.println("----- product id -----");
                int productId = new Scanner(System.in).nextInt();
                boolean idIsOk = productRepository.checkgoodsId(productId);
                if(idIsOk){
                    int numberOfProduct = productRepository.findNumberOfProduct(productId);
                    System.out.println("number : " + numberOfProduct);
                    System.out.println("----- amount -----");
                    System.out.println("This number will be deducted from the current balance");
                    int amount = new Scanner(System.in).nextInt();
                    if(amount < numberOfProduct){
                        int inventory = numberOfProduct - amount;
                        productRepository.updateInventory(inventory,productId);
                        break;
                    }
                    else{
                        System.out.println("inventory is more less than this number you can't do that");
                    }
                }else{
                    System.out.println("this product isn't in the inventory");
                    System.out.println("1.try again          2.back to menu");
                    int choice = new Scanner(System.in).nextInt();
                    while(choice < 1 || choice > 2){
                        System.out.println("you should choose between menu options");
                        System.out.print("try again: ");
                        choice = new Scanner(System.in).nextInt();
                    }
                    if(choice == 2){
                        break;
                    }
                }
            }catch (InputMismatchException exception){
                System.out.println("you should enter a number");
                System.out.println("try again: ");
            }
        }
    }
}
