package com.gatech.astroworld.spacetrader.model.Goods;

import com.gatech.astroworld.spacetrader.entity.GoodType;
import com.gatech.astroworld.spacetrader.model.Planet;
import com.gatech.astroworld.spacetrader.model.SolarSystem;

public class TradeGood {

    private int quantity;
    private  String name;
    private int price;
    private int sellCount;
    private SolarSystem sys;
    private GoodType good;
    private Planet plan;
    private int resID;
    public TradeGood(GoodType good, Planet p) {
        this.good = good;
        this.name = good.getName();
        this.plan = p;
        if (plan != null) {
            this.sys = p.getSys();
        }
        this.resID = good.getResourceID();

    }

    public TradeGood(String goodName, int price, int quantity) {
        this.price = price;
        this.quantity = quantity;
        this.good = GoodType.valueOf(goodName);
    }

    public int calculatePrice() {
        int newPrice = 0;
        newPrice = good.getBasePrice() + (good.getIPL() * sys.getTechLevel().ordinal()) +
                good.getVar();
        return newPrice;
    }

    public void setSys(SolarSystem system) {
        sys = system;
    }

    public void setPlanet(Planet p) {

    }

    public void setQuantity(int q) {
        quantity = q;
    }

    public void setPrice(int p) {
        price = p;
    }

    public int getSellCount() {
        return sellCount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setSellCount(int c) {
        sellCount = c;
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

    public void setName(String n) {
        name = n;
    }

    public int getResID() {
        return resID;
    }

}
