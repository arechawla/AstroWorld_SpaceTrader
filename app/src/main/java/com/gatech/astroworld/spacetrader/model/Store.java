package com.gatech.astroworld.spacetrader.model;

import com.gatech.astroworld.spacetrader.entity.Good;
import com.gatech.astroworld.spacetrader.entity.Player;

import java.util.List;

public class Store {

    private List<Good> storeInventory;
    private int storeCredits;
    private List<Good> cart;



    public void buy(Player buyer) {
        int availSpace = buyer.getShip().getCapacity() - buyer.getShip().getCargoList().size();
        if (cart.size() <= availSpace && buyer.getCredits() >= cartTotal()) {
            for (Good item: cart) {
                buyer.getShip().getCargoList().add(item);
            }
        }
        cart = null;
    }

    public void addToCart(Good good) {
        cart.add(good);
    }


    public int cartTotal() {
        int total = 0;
        for (Good item: cart) {
            total += item.getPrice();
        }
        return total;
    }


}
