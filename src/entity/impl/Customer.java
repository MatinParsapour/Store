package entity.impl;

import entity.BaseEntity;

public class Customer implements BaseEntity<String> {
    private static String name;
    private static String username;
    private static String password;
    private static int balance;


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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
