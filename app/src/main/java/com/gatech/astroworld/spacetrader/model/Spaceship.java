package com.gatech.astroworld.spacetrader.model;

import com.gatech.astroworld.spacetrader.entity.GoodType;
import com.gatech.astroworld.spacetrader.model.Goods.TradeGood;

import java.util.ArrayList;
import java.util.List;

public class Spaceship {

    private String name;
    private List<TradeGood> cargo;
    private int capacity;
    private int fuel;

    public Spaceship (String name, int capacity, int fuel) {
        this.name = name;
        this.cargo = new ArrayList<>();
        this.capacity = capacity;
        this.fuel = fuel;
    }

    public List<TradeGood> getCargoList() {
        return cargo;
    }

    public int cargoAmount() {
        int total = 0;
        for (TradeGood item: cargo) {
            total += item.getQuantity();
        }
        return total;
    }

    public Integer containsCargo(TradeGood find) {
        for (int i = 0; i < cargo.size(); i++) {
            if (find.getName().equals(cargo.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }


    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return name;
    }



}
