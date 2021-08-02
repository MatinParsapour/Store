package entity.impl;

import entity.BaseEntity;

public class Admin implements BaseEntity<String> {
    private static String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
