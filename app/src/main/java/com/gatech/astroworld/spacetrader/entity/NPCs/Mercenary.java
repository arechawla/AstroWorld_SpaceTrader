package com.gatech.astroworld.spacetrader.entity.NPCs;

import com.gatech.astroworld.spacetrader.model.Game;

public class Mercenary {
    //MERCENARIES ALWAYS ATTACK
    private int attack;
    private int lifePoints;

    public Mercenary(int attack, int lifePoints) {
        this.attack = attack;
        this.lifePoints = lifePoints;
    }

    public void interact() {
        attack();
    }

    public void avoid() {
        return;
    }

    public void attack() {

    }

}
