package com.gatech.astroworld.spacetrader.model;

import com.gatech.astroworld.spacetrader.entity.GoodType;
import com.gatech.astroworld.spacetrader.entity.TechLevel;
import com.gatech.astroworld.spacetrader.model.Goods.MarketGood;
import com.gatech.astroworld.spacetrader.model.Goods.TradeGood;
import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Store {

    private List<MarketGood> storeInventory;
    private int storeCredits;
    private TechLevel techLev;
    @Exclude
    private Planet plan;


//    public Store(int storeCredits, SolarSystem sys, Planet plan)
    public Store(int storeCredits, Planet p) {
        this.storeCredits = storeCredits;
        this.techLev = p.getSys().getTechLevel();
        this.plan = p;
        this.storeInventory = new ArrayList<>();
        populateStoreInventory();
    }
    
    public List<MarketGood> populateStoreInventory() {
        GoodType[] goods = GoodType.values();
        for (GoodType good: goods) {
            MarketGood mark = new MarketGood(good, plan);
            mark.setQuantity(calculateQuantity(good));
            System.out.println(mark.getQuantity());
            if (mark.getQuantity() != 0) {
                storeInventory.add(mark);
            }
        }
        return storeInventory;
    }

    @Exclude
    public Planet getPlanet() {
        return plan;
    }

    @Exclude
    public void  setPlan(Planet p) {
        plan = p;
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

    public TechLevel getTechLev() {
        return  techLev;
    }


    public void zeroMarketCounts() {
        for (MarketGood good: storeInventory) {
            good.setCount(0);
        }
    }

    public void setStoreCredits(int storeCredits) {
        this.storeCredits = storeCredits;
    }

    public void setStoreInventory(List<MarketGood> st) {
        this.storeInventory = st;
    }

    public int getStoreCredits() {
        return storeCredits;
    }

    private int calculateQuantity(GoodType item) {
        int base = 10;
        if (techLev.ordinal() < item.getMTLP()) {
            return 0;
        } else {
            Random ranCalc = new Random();
            int upperBound = 10;
            base += ranCalc.nextInt(upperBound);

            if (techLev.ordinal() == item.getTTP()) {
                upperBound =15;
                base += ranCalc.nextInt(upperBound);
            }

        }
        return base;
    }


    public void buy(Player buyer) {
        ArrayList<TradeGood> cartBuy = new ArrayList<>();
        int total = 0;
        List<MarketGood> toRemove = new ArrayList<MarketGood>();
        for (MarketGood mark: storeInventory) {
            int markQuant = mark.getQuantity();
            int markCount = mark.getCount();
            if (markCount > 0) {
                TradeGood tGood = new TradeGood(mark.getGoodType());
                tGood.setQuantity(markCount);
                tGood.setPrice(mark.getPrice());
                total += mark.getPrice() * mark.getCount();
                cartBuy.add(tGood);
                mark.setQuantity(markQuant - markCount);
                int index = buyer.getShip().containsCargo(tGood);
                if (index == -1) {
                    buyer.getShip().getCargoList().add(tGood);
                } else {
                    int origQuant = buyer.getShip().getCargoList().get(index).getQuantity();
                    buyer.getShip().getCargoList().get(index).setQuantity(origQuant + markCount);
                }
                if (markQuant - markCount == 0) {
                    toRemove.add(mark);
                } else {
                    mark.setCount(0);
                }
            }

        }
        for (MarketGood m: toRemove) {
            storeInventory.remove(m);
        }
        int playerCreds = Game.getInstance().getPlayer().getCredits();
        Game.getInstance().getPlayer().setCredits(playerCreds - total);
    }



    public void incrementCountBuy(MarketGood good) {

        good.setCount(good.getCount() + 1);

    }

    public void decrementCountBuy(MarketGood good) {
        int i = good.getCount() - 1;
        if (i >= 0) {
            good.setCount(i);

        }
    }

    public void incrementCountSell(TradeGood good) {
        int i = good.getSellCount() + 1;
        if (i <= good.getQuantity()) {
            good.setSellCount(i);
        }
    }


    public void decrementCountSell(TradeGood good) {
        int i = good.getSellCount() - 1;
        if (i >= 0) {
            good.setSellCount(i);
        }
    }


    public void sell(Player player) {
        List<TradeGood> list = Game.getInstance().
                getPlayer().getShip().getCargoList();
        int total = 0;
        List<TradeGood> toRemove = new ArrayList<TradeGood>();
        for (TradeGood i: list) {
            total += i.getPrice() * i.getSellCount();
        }
        for (TradeGood gSold: list) {
            boolean alreadyAdded = false;
            for (MarketGood gMark: storeInventory) {
                if (gSold.getName().equals(gMark.getName())) {
                    int orig = gMark.getQuantity();
                    alreadyAdded = true;
                    gMark.setQuantity(orig + gSold.getSellCount());
                }
            }
            if (!alreadyAdded) {
                MarketGood diffGood = new MarketGood(gSold.getGoodType(), plan);
                diffGood.setQuantity(gSold.getSellCount());
                diffGood.setSolarSystem(player.getCurrentSystem());
                diffGood.setPlanet(player.getCurrentPlanet());
                storeInventory.add(diffGood);
            }
            if (gSold.getSellCount() == gSold.getQuantity()) {
                toRemove.add(gSold);
            } else {
                gSold.setQuantity(gSold.getQuantity() - gSold.getSellCount());
                gSold.setSellCount(0);
            }
        }
        for (TradeGood t: toRemove) {
            list.remove(t);
        }
        player.setCredits(player.getCredits() + total);
    }

    public List<MarketGood> getStoreInventory() { return storeInventory; }



}
