package com.gatech.astroworld.spacetrader.model;

import com.gatech.astroworld.spacetrader.entity.GoodType;
import com.gatech.astroworld.spacetrader.entity.Player;

import java.util.List;

public class Store {

    private List<GoodType> storeInventory;
    private int storeCredits;
    private List<GoodType> cart;



    public void buy(Player buyer) {
        int availSpace = buyer.getShip().getCapacity() - buyer.getShip().cargoAmount();
        if (cart.size() <= availSpace && buyer.getCredits() >= cartTotal()) {
            for (GoodType item: cart) {
                Integer index = buyer.getShip().containsCargo(item);
                int originalQuanity = buyer.getShip().getCargoList().get(index).getQuantity();
                if (index != null) {
                    buyer.getShip().getCargoList().get(index).setQuantity(originalQuanity +=
                            item.getQuantity());
                } else {
                    buyer.getShip().getCargoList().add(item);
                }
            }
        }
        cart = null;
    }

    public void addToCart(GoodType GoodType) {
        cart.add(GoodType);
    }


    public int cartTotal() {
        int total = 0;
        for (GoodType item: cart) {
            total += item.getPrice();
        }
        return total;
    }


}
