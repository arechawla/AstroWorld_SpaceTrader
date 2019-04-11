package com.gatech.astroworld.spacetrader.entity.NPCs;

import com.gatech.astroworld.spacetrader.model.Goods.TradeGood;


public class Trader {

    private int attack;
    private int lifePoints;
    private TradeGood goodOffered;

    public Trader(int attack, int lifePoints, TradeGood goodOffered) {
        this.attack = attack;
        this.lifePoints = lifePoints;
        this.goodOffered = goodOffered;
    }

    //an NPC may interact w/ you, depending on your statistics
    public void interact() {
        //if surrender boolean is true then he makes cheaper offer
        //if you don't take the offer you gotta leave
        //transaction
        //the amount that you request if you have enough money and space is
        //added to your inventory
    }

    //an NPC may avoid you, depending your statistics
    public void avoid() {
        //boolean ignore
        //true if it ignores you false otherwise
    }

    //and NPC may attack you, dependent on your stats
    public void attack() {
        return;
    }


}
