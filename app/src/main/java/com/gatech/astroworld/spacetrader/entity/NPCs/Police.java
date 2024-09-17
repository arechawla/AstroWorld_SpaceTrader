package com.gatech.astroworld.spacetrader.entity.NPCs;

import com.gatech.astroworld.spacetrader.entity.EncounterNPC;
import com.gatech.astroworld.spacetrader.model.Game;

public class Police implements EncounterNPC {

    private int attack;
    private int lifePoints;
    private int bribeLevel;

    public Police(int attack, int lifePoints, int bribeLevel) {
        this.attack = attack;
        this.lifePoints = lifePoints;
        this.bribeLevel = bribeLevel;
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

    }

    public void attack() {
        //ATTACK IN TERMS OF POLICE = INSPECTION
    }


}
