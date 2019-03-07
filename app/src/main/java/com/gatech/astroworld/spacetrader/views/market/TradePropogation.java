package com.gatech.astroworld.spacetrader.views.market;

import com.gatech.astroworld.spacetrader.entity.GoodType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TradePropogation {

    public static final List<GoodType> ITEMS = new ArrayList<>();
    public static final Map<String, GoodType> ITEM_MAP = new HashMap<>();

    private static int goodCount = 25;
    public TradePropogation() {

        // Add goods to item list
        for (int i = 0; i < 3; i++) {
            addItem(GoodType.FIREARMS);
        }
    }

    private static void addItem(GoodType item) {
        ITEMS.add(item);
        ITEM_MAP.put(String.valueOf(item.ordinal()), item);
    }
    public List<GoodType> getItems(){
        return ITEMS;
    }

}


