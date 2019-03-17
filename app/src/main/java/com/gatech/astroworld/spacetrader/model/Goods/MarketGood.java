package com.gatech.astroworld.spacetrader.model.Goods;

import com.gatech.astroworld.spacetrader.entity.GoodType;
import com.gatech.astroworld.spacetrader.model.Game;
import com.gatech.astroworld.spacetrader.model.Planet;
import com.gatech.astroworld.spacetrader.model.SolarSystem;

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
