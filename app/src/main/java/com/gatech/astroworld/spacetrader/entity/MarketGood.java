package com.gatech.astroworld.spacetrader.entity;

import com.gatech.astroworld.spacetrader.model.Planet;
import com.gatech.astroworld.spacetrader.model.SolarSystem;

public class MarketGood {
    private GoodType good;
    private int count;
    private Planet planet;
    private SolarSystem sys;

    public MarketGood(GoodType good) {
        this.good = good;
        this.count = count;
    }

    private int calculatePrice() {
        int newPrice = 0;
        newPrice = good.getBasePrice() + (good.getIPL() * sys.getTechLevel().ordinal());
        return newPrice;
    }

}
