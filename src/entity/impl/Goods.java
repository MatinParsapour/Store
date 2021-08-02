package entity.impl;

import entity.BaseEntity;

public class Goods implements BaseEntity<String> {
    private static int goodsId;
    private static String name;
    private static String category;
    private static String subcategory;
    private static int cost;
    private static int number;


    public static String getCategory() {
        return category;
    }

    public static void setCategory(String category) {
        Goods.category = category;
    }

    public static String getSubcategory() {
        return subcategory;
    }

    public static void setSubcategory(String subcategory) {
        Goods.subcategory = subcategory;
    }

    public static int getCost() {
        return cost;
    }

    public static void setCost(int cost) {
        Goods.cost = cost;
    }

    public static int getNumber() {
        return number;
    }

    public static void setNumber(int number) {
        Goods.number = number;
    }

    public static int getGoodsId() {
        return goodsId;
    }

    public static void setGoodsId(int goodsId) {
        Goods.goodsId = goodsId;
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
