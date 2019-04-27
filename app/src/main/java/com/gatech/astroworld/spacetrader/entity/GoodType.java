package com.gatech.astroworld.spacetrader.entity;

import com.gatech.astroworld.spacetrader.R;

import static com.gatech.astroworld.spacetrader.entity.Resources.ARTISTIC;
import static com.gatech.astroworld.spacetrader.entity.Resources.LOTSOFHERBS;
import static com.gatech.astroworld.spacetrader.entity.Resources.LOTSOFWATER;
import static com.gatech.astroworld.spacetrader.entity.Resources.MINERALRICH;
import static com.gatech.astroworld.spacetrader.entity.Resources.RICHFAUNA;
import static com.gatech.astroworld.spacetrader.entity.Resources.RICHSOIL;
import static com.gatech.astroworld.spacetrader.entity.Resources.WARLIKE;
import static com.gatech.astroworld.spacetrader.entity.Resources.WEIRDMUSHROOMS;

public enum GoodType {
    WATER("Water", 0, 0, 2, 30, 3, 4, LOTSOFWATER, 30, 50, R.drawable.water_icon),
    FURS("Furs", 0, 0, 0, 250, 10, 10, RICHFAUNA, 230, 280, R.drawable.furs_icon),
    ORE("Ore", 2, 2, 3, 350, 20, 10, MINERALRICH, 350, 420, R.drawable.ore_image),
    FOOD("Food", 1, 0, 1, 100, 5, 5, RICHSOIL, 90, 160, R.drawable.food_icon),
    GAMES("Games", 3, 1, 6, 250, -10, 5, ARTISTIC, 160, 270, R.drawable.games_icon),
    FIREARMS("Firearms", 3, 1, 5, 1250, -75, 100, WARLIKE, 600, 1100, R.drawable.firearm_icon),
    MEDICINE("Medicine", 4, 1, 6, 650, -20, 10, LOTSOFHERBS, 400, 700, R.drawable.medicine_icon),
    NARCOTICS("Narcotics", 5, 0, 5, 3500, -125, 150, WEIRDMUSHROOMS, 2000, 3000, R.drawable.narcs_icon),
    ROBOTS("Robots", 6, 4, 7, 5000, -150, 100, null, 3500, 5000, R.drawable.robot_icron);

    private String name;
    private int size;
    private int rarity;
    private GoodType type;
    private int MTLP; //min tech level to produce resource
    private int MTLU; //minimum tech to use resource
    private int TTP; //tech level which produces most of this item
    private int basePrice;
    private int IPL; //price increase per tech level
    private int var; //variance is macimum percent that the price can vary from base
    private Resources conditionModifier; //when his is present, pricce is unusually low
    private int minTrader; //min price offered by random trader (not planet)
    private int maxTrader; //max price offered by random trader (not planet)
    private int resourceID; //resourceID for image

    GoodType(String name, int MTLP, int MTLU, int TTP, int basePrice,  int IPL, int var,
                Resources conditionModifier, int minTrader, int maxTrader, int resourceID) {
        this.name = name;
        this.MTLP = MTLP;
        this.MTLU = MTLU;
        this.TTP = TTP;
        this.basePrice = basePrice;
        this.IPL = IPL;
        this.var = var;
        this.conditionModifier = conditionModifier;
        this.minTrader = minTrader;
        this.maxTrader = maxTrader;
        this.resourceID = resourceID;
    }
    public String getName() {
        return name;
    }

    public int getMTLP() {
        return MTLP;
    }

    public int getMTLU() {
        return MTLU;
    }

    public int getTTP() {
        return TTP;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public int getIPL() {
        return IPL;
    }

    public int getVar() {
        return var;
    }

    public int getResourceID() {
        return resourceID;
    }

    public Resources getConditionModifier() {
        return conditionModifier;
    }

    public int getMinTrader() {
        return minTrader;
    }

    public int getMaxTrader() {
        return maxTrader;
    }


}






