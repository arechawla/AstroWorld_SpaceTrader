package com.gatech.astroworld.spacetrader.entity;

enum GoodType {
    WATER,
    FURS,
    ORE,
    FOOD,
    GAMES,
    FIREARMS,
    MEDICINE,
    NARCOTICS,
    ROBOTS,
    NONE
}

public class Good {
    private String name;
    private int price;
    private int size;
    private int rarity;
    private GoodType type;
    private int quantity;
    public Good(String name, int price, int size, int rarity, int quantity) {
        this.name = name;
        this.price = price;
        this.size = size;
        this.rarity = rarity;
        this.type = GoodType.NONE;
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }
}