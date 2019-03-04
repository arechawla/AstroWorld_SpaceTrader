package com.gatech.astroworld.spacetrader.model;

import com.gatech.astroworld.spacetrader.entity.GoodType;
import com.gatech.astroworld.spacetrader.entity.Player;

import java.util.List;

public class Store {

    private List<MarketGood> storeInventory;
    private int storeCredits;
    private List<GoodType> cart;



    public void buy(Player buyer) {
        int availSpace = buyer.getShip().getCapacity() - buyer.getShip().cargoAmount();
        if (cart.size() <= availSpace && buyer.getCredits() >= cartTotal()) {
            for (GoodType item: cart) {
                Integer index = buyer.getShip().containsCargo(item);
                int originalQuanity = buyer.getShip().getCargoList().get(index).getQuantity();
                if (index != null) {
                    buyer.getShip().getCargoList().get(index).setQuantity(originalQuanity +
                            item.getQuantity());
                } else {
                    buyer.getShip().getCargoList().add(item);
                }
            }
        }
        cart = null;
    }

    public void addToCart() {
        for (int i = 0; i < storeInventory.size(); i++) {
            int quantBuy = storeInventory.get(i).getCount();
            if (quantBuy != 0) {
                GoodType good = storeInventory.get(i).getGoodType();
                good.setQuantity(storeInventory.get(i).getCount());
                good.setPrice(storeInventory.get(i).getPrice());
                cart.add(good);
            }
        }
    }


    public int cartTotal() {
        int total = 0;
        for (GoodType item: cart) {
            total += item.getPrice();
        }
        return total;
    }

    private class MarketGood {
        private GoodType good;
        private int count;
        private Planet planet;
        private SolarSystem sys;
        private int quantity;
        private int price;

        public MarketGood(GoodType good, int count, int quantity, int price,
                          SolarSystem sys, Planet planet) {
            this.good = good;
            this.count = count;
            this.quantity = quantity;
            this.price = price;

        }

        private int calculatePrice() {
            int newPrice = 0;
            newPrice = good.getBasePrice() + (good.getIPL() * sys.getTechLevel().ordinal());
            return newPrice;
        }

        public int getCount() {
            return count;
        }

        public GoodType getGoodType() {
            return good;
        }

        public int getPrice() {
            return price;
        }

    }




}
