package com.gatech.astroworld.spacetrader.entity.NPCs;

import android.widget.Toast;

import com.gatech.astroworld.spacetrader.entity.EncounterNPC;
import com.gatech.astroworld.spacetrader.model.Game;
import com.gatech.astroworld.spacetrader.model.Goods.TradeGood;

import java.util.List;

public class Trader implements EncounterNPC {

    private int attack;
    private int lifePoints;
    private TradeGood goodOffered;

    public Trader(int attack, int lifePoints, TradeGood goodOffered) {
        this.attack = attack;
        this.lifePoints = lifePoints;
        this.goodOffered = goodOffered;
    }

    public void interact() {
        int interaction = (int) (Math.random() * 100);
        if (interaction > 25) {
            avoid();
        } else {
            if (Game.getInstance().getPlayer().getReputation() < 20) {
                attack();
            }
        }
    }

    public void avoid() {
        return;
    }

    public void attack() {

    }

    public void trade() {

    }

    public void surrender() {

    }


}
