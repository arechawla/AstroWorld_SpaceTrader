package com.gatech.astroworld.spacetrader.model;

import android.widget.Toast;

import com.gatech.astroworld.spacetrader.entity.GoodType;
import com.gatech.astroworld.spacetrader.entity.Player;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Store {

    private ArrayList<MarketGood> storeInventory;
    private int storeCredits;
    private ArrayList<GoodType> cartBuy;
    private ArrayList<MarketGood> cartSell;
    private SolarSystem sys;
    private Planet plan;
    private int buyTotal;

//    public Store(int storeCredits, SolarSystem sys, Planet plan)
    public Store(int storeCredits) {
        this.storeCredits = storeCredits;
        this.sys = Game.getInstance().getPlayer().getCurrentSystem();
        this.plan = Game.getInstance().getPlayer().getCurrentPlanet();
        this.storeInventory = new ArrayList<>();
        this.cartBuy = new ArrayList<>();
        this.cartSell = new ArrayList<>();
    }


    public ArrayList<MarketGood> populateStoreInventory() {
        GoodType[] goods = GoodType.values();
        for (GoodType good: goods) {
            MarketGood mark = new MarketGood(good);
            mark.setQuantity(calculateQuantity(good));
            System.out.println(mark.getQuantity());
            if (mark.getQuantity() != 0) {
                storeInventory.add(mark);
            }
        }
        return storeInventory;
    }

    /**
     * for testing purposes
     * @return goodtypes as strings
     */
    public String[] getListString() {
        GoodType[] goods = GoodType.values();
        String[] StringGoods = new String[goods.length];
        for (int i = 0; i < goods.length; i++) {
            StringGoods[i] = goods[i].name();
        }
        return StringGoods;
    }



    private int calculateQuantity(GoodType item) {
        int base = 10;
        if (sys.getTechLevel().ordinal() < item.getMTLP()) {
            return 0;
        } else {
            Random ranCalc = new Random();
            int upperBound = 10;
            base += ranCalc.nextInt(upperBound);

            if (sys.getTechLevel().ordinal() == item.getTTP()) {
                upperBound =15;
                base += ranCalc.nextInt(upperBound);
            }

        }
        return base;
    }


    public void buy(Player buyer) {
        int total = 0;
        for (MarketGood mark: storeInventory) {
            if (mark.count > 0) {
                GoodType good = mark.getGoodType();
                good.setQuantity(mark.getCount());
                good.setPrice(mark.getPrice());
                total += mark.getPrice() * mark.getCount();
                mark.setQuantity(mark.quantity - mark.count);
                cartBuy.add(good);
            }
        }
        for (GoodType item: cartBuy) {
            Integer index = buyer.getShip().containsCargo(item);
            if (index == -1) {
                buyer.getShip().getCargoList().add(item);
            } else {
                int originalQuantity = buyer.getShip().getCargoList().get(index).getQuantity();
                buyer.getShip().getCargoList().get(index).setQuantity(originalQuantity +
                        item.getQuantity());
            }
        }
        int playerCreds = Game.getInstance().getPlayer().getCredits();
        Game.getInstance().getPlayer().setCredits(playerCreds - total);
    }



    public void incrementCountBuy(MarketGood good) {

        good.setCount(good.getCount() + 1);
        buyTotal += good.getPrice();
    }

    public void decrementCountBuy(MarketGood good) {
        int i = good.count - 1;
        if (i >= 0) {
            good.setCount(i);
            buyTotal -= good.getPrice();
        }
    }

    public void incrementCountSell(GoodType good) {
        int i = good.getSellCount() + 1;
        if (i <= good.getQuantity()) {
            good.setSellCount(i);
        }
    }


    public void decrementCountSell(GoodType good) {
        int i = good.getSellCount() - 1;
        if (i >= 0) {
            good.setSellCount(i);
        }
    }


    public void addToCartSell(Player player) {
        List<GoodType> list = player.getShip().getCargoList();

        for (int i = 0; i < list.size(); i++) {
            int quantSell = list.get(i).getSellCount();
            if (quantSell != 0) {
                MarketGood good = new MarketGood(list.get(i));
                good.setPrice( list.get(i).getBasePrice());
                good.setQuantity(quantSell);
                cartSell.add(good);
                int orig = list.get(i).getQuantity();
                list.get(i).setQuantity(orig - quantSell);
            }
        }
        for (GoodType good: list) {
            good.setSellCount(0);
        }
    }

    public void sell(Player player) {
        List<GoodType> list = Game.getInstance().getPlayer().getShip().getCargoList();
        int total = 0;
        for (GoodType i: list) {
            total += i.getPrice()*i.getSellCount();
        }
        for (GoodType gSold: list) {
            boolean alreadyAdded = false;
            for (MarketGood gMark: storeInventory) {
                if (gSold.getName().equals(gMark.getName())) {
                    int orig = gMark.getQuantity();
                    alreadyAdded = true;
                    gMark.setQuantity(orig + gSold.getSellCount());
                }
            }
            if (!alreadyAdded) {
                MarketGood diffGood = new MarketGood(gSold);
                diffGood.setSolarSystem(player.getCurrentSystem());
                diffGood.setPlanet(player.getCurrentPlanet());
                storeInventory.add(diffGood);
            }
        }
        player.setCredits(player.getCredits() + total);
    }






    public int cartSellTotal() {
        int total = 0;
        for (MarketGood item: cartSell) {
            total += item.getPrice();
        }
        return total;
    }


    public List<MarketGood> getStoreInventory() { return storeInventory; }

    public class MarketGood {
        private GoodType good;
        private String name;
        private int count;
        private Planet planet;
        private SolarSystem sys;
        private int quantity;
        private int price;

        public MarketGood(GoodType good) {
            this.good = good;
            this.name = good.getName();
            this.sys = Game.getInstance().getPlayer().getCurrentSystem();
            this.planet = Game.getInstance().getPlayer().getCurrentPlanet();
            this.price = calculatePrice();
        }

        public int calculatePrice() {
            int newPrice = 0;
            newPrice = good.getBasePrice() + (good.getIPL() * sys.getTechLevel().ordinal()) +
                    good.getVar();
            return newPrice;
        }

        public void setQuantity(int q) {
            quantity = q;
        }


        public void setPrice(int p) {
            price = p;
        }

        public int getCount() {
            return count;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setCount(int c) {
            count = c;
        }

        public GoodType getGoodType() {
            return good;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        public void setSolarSystem(SolarSystem system) {
            sys = system;
        }

        public void setPlanet(Planet plan) {
            planet = plan;
        }


    }




}
