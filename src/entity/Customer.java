package entity;

public class Customer {
    private static String name;
    private static String username;
    private static String password;
    private static int balance;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Customer.name = name;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Customer.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Customer.password = password;
    }

    public static int getBalance() {
        return balance;
    }

    public static void setBalance(int balance) {
        Customer.balance = balance;
    }
}
