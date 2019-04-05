package com.gatech.astroworld.spacetrader.model.Goods;

import com.gatech.astroworld.spacetrader.entity.GoodType;
import com.gatech.astroworld.spacetrader.model.Planet;
import com.gatech.astroworld.spacetrader.model.SolarSystem;

public class TradeGood {

    private int quantity;
    private  String name;
    private int price;
    private int sellCount;
    private GoodType good;

    public TradeGood(GoodType good) {
        this.good = good;
        this.name = good.getName();

    }

    public TradeGood(String goodName, int price, int quantity) {
        this.price = price;
        this.quantity = quantity;
        this.good = GoodType.valueOf(goodName);
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

}
