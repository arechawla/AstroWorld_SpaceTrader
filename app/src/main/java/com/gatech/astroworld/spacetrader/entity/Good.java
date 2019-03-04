package com.gatech.astroworld.spacetrader.entity;

import static com.gatech.astroworld.spacetrader.entity.Resources.ARTISTIC;
import static com.gatech.astroworld.spacetrader.entity.Resources.LOTSOFHERBS;
import static com.gatech.astroworld.spacetrader.entity.Resources.LOTSOFWATER;
import static com.gatech.astroworld.spacetrader.entity.Resources.MINERALRICH;
import static com.gatech.astroworld.spacetrader.entity.Resources.RICHFAUNA;
import static com.gatech.astroworld.spacetrader.entity.Resources.RICHSOIL;
import static com.gatech.astroworld.spacetrader.entity.Resources.WARLIKE;
import static com.gatech.astroworld.spacetrader.entity.Resources.WEIRDMUSHROOMS;

enum GoodType {
    WATER("Water", 0, 0, 2, 30, 3, 4.0, LOTSOFWATER, 30, 50),
    FURS("Furs", 0, 0, 0, 250, 10, 10.0, RICHFAUNA, 230, 280),
    ORE("Ore", 2, 2, 3, 350, 20, 10.0, MINERALRICH, 350, 420),
    FOOD("Food", 1, 0, 1, 100, 5, 5.0, RICHSOIL, 90, 160),
    GAMES("Games", 3, 1, 6, 250, -10, 5.0, ARTISTIC, 160, 270),
    FIREARMS("Firearms", 3, 1, 5, 1250, -75, 100.0, WARLIKE, 600, 1100),
    MEDICINE("Medicine", 4, 1, 6, 650, -20, 10.0, LOTSOFHERBS, 400, 700),
    NARCOTICS("Narcotics", 5, 0, 5, 3500, -125, 150.0, WEIRDMUSHROOMS, 2000, 3000),
    ROBOTS("Robots", 6, 4, 7, 5000, -150, 100.0, null, 3500, 5000);

    private String name;
    private int price;
    private int size;
    private int rarity;
    private GoodType type;

    private int MTLP; //min tech level to produce resource
    private int MTLU; //minimum tech to use resource
    private int TTP; //tech level which produces most of this item
    private int basePrice;
    private int IPL; //price increase per tech level
    private double var; //variance is macimum percent that the price can vary from base
    private Resources conditionModifier; //when his is present, pricce is unusually low
    private int minTrader; //min price offered by random trader (not planet)
    private int maxTrader; //max price offered by random trader (not planet)

    GoodType(String name, int MTLP, int MTLU, int TTP, int basePrice,  int IPL, double var,
                Resources conditionModifier, int minTrader, int maxTrader) {
        this.name = name;
        this.MTLP = MTLP;
        this.MTLU = MTLU;
        this.TTP = TTP;
        this.IPL = IPL;
        this.var = var;
        this.conditionModifier = conditionModifier;
        this.minTrader = minTrader;
        this.maxTrader = maxTrader;
    }

    public int getPrice() {
        return price;
    }


}






