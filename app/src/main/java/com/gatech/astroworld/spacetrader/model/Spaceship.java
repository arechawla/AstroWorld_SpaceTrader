package com.gatech.astroworld.spacetrader.model;

import com.gatech.astroworld.spacetrader.entity.Good;

import java.util.ArrayList;
import java.util.List;

public class Spaceship {

    private String name;
    private List<Good> goods;
    private int capacity;
    private int fuel;

    public Spaceship (String name, int capacity, int fuel) {
        this.name = name;
        this.goods = new ArrayList<>();
        this.capacity = capacity;
        this.fuel = fuel;
    }
    @Override
    public String toString() {
        return name;
    }
}
