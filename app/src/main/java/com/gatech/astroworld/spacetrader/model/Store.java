package com.gatech.astroworld.spacetrader.model;

import com.gatech.astroworld.spacetrader.entity.GoodType;
import com.gatech.astroworld.spacetrader.entity.TechLevel;
import com.gatech.astroworld.spacetrader.model.Goods.MarketGood;
import com.gatech.astroworld.spacetrader.model.Goods.TradeGood;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Store {

    private List<MarketGood> storeInventory;
    private int storeCredits;
    private TechLevel techLev;
    @Exclude
    private Planet plan;



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
        List<TradeGood> cartBuy = new ArrayList<>();
        int total = 0;
        List<MarketGood> toRemove = new ArrayList<MarketGood>();
        for (MarketGood mark: storeInventory) {
            int markQuant = mark.getQuantity();
            int markCount = mark.getCount();
            if (markCount > 0) {
                TradeGood tGood = new TradeGood(mark.getGoodType(), null);
                tGood.setQuantity(markCount);
                tGood.setPrice(mark.getPrice());
                total += mark.getPrice() * mark.getCount();
                cartBuy.add(tGood);
                int newQuant = markQuant - markCount;
                mark.setQuantity(newQuant);
                int[] arr = {1, 10, 11, 12, 13, 14, 15, 2, 3, 4, 5,
                6, 7, 8, 9};

                int sysRef = arr[Game.getInstance().getPlayer().getCurSystemReference()];
                int planRef = Game.getInstance().getPlayer().getCurPlanetReference() + 1;

                FirebaseDatabase.getInstance().getReference().child("systemList").
                        child("System " + sysRef)
                        .child("listPlanets").child("Planet " + planRef)
                        .child("store").child("store inventory").child(mark.getName()).child(
                                "quantity").setValue(newQuant);
                int index = buyer.getShip().containsCargo(tGood);
                if (index == -1) {
                    buyer.getShip().getCargoList().add(tGood);
                } else {
                    int origQuant = buyer.getShip().getCargoList().get(index).getQuantity();
                    buyer.getShip().getCargoList().get(index).setQuantity(origQuant + markCount);
                }
                if ((markQuant - markCount) == 0) {
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
                    int newQuant = orig + gSold.getSellCount();
                    gMark.setQuantity(newQuant);
                    int[] arr = {1, 10, 11, 12, 13, 14, 15, 2, 3, 4, 5,
                            6, 7, 8, 9};
                    int sysRef = arr[Game.getInstance().getPlayer().getCurSystemReference()];
                    int planRef = Game.getInstance().getPlayer().getCurPlanetReference() + 1;
                    FirebaseDatabase.getInstance().getReference().child("systemList").
                            child("System " + sysRef)
                            .child("listPlanets").child("Planet " + planRef)
                            .child("store").child("store inventory").child(gMark.getName()).child(
                                    "quantity").
                            setValue(newQuant);
                }
            }
            if (!alreadyAdded) {
                MarketGood diffGood = new MarketGood(gSold.getGoodType(), plan);
                diffGood.setQuantity(gSold.getSellCount());
                diffGood.setSolarSystem(player.getCurrentSystem());
                diffGood.setPlanet(player.getCurrentPlanet());
                storeInventory.add(diffGood);
                int[] arr = {1, 10, 11, 12, 13, 14, 15, 2, 3, 4, 5,
                        6, 7, 8, 9};
                int sysRef = arr[Game.getInstance().getPlayer().getCurSystemReference()];
                int planRef = Game.getInstance().getPlayer().getCurPlanetReference() + 1;
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(
                        "systemList").
                        child("System " + sysRef)
                        .child("listPlanets").child("Planet " + planRef)
                        .child("store").child("store inventory").child(diffGood.getName());
                ref.child("quantity").
                        setValue(gSold.getSellCount());
                ref.child("price").setValue(gSold.getPrice());
                ref.child("name").setValue(gSold.getName());
                ref.child("goodType").setValue(gSold.getGoodType());
            }
            gSold.setQuantity(gSold.getQuantity() - gSold.getSellCount());
            Save.saveSpaceShipInformation();
            if (gSold.getQuantity() == 0) {
                toRemove.add(gSold);
            } else {
                gSold.setSellCount(0);
            }
        }
        for (TradeGood t: toRemove) {
            list.remove(t);
        }
        player.getShip().setCargoList(list);
        Save.saveSpaceShipInformation();
        player.setCredits(player.getCredits() + total);
    }

    public List<MarketGood> getStoreInventory() { return storeInventory; }



}
