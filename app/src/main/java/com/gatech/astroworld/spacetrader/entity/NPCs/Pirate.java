package com.gatech.astroworld.spacetrader.entity.NPCs;

import com.gatech.astroworld.spacetrader.entity.EncounterNPC;
import com.gatech.astroworld.spacetrader.model.Game;

public class Pirate implements EncounterNPC {

    private int attack;
    private int lifePoints;

    public Pirate(int attack, int lifePoints) {
        this.attack = attack;
        this.lifePoints = lifePoints;
    }

    public void interact() {
        int interaction = (int) (Math.random() * 100);
        if (interaction > 5) {
            if (Game.getInstance().getPlayer().getReputation() < 20) {
                avoid();
            } else {
                attack();
            }
        } else {
            avoid();
        }
    }

    public void avoid() {
        //AVOID AND FLEE ARE ESSENTIALLY THE SAME THING
        return;
    }

    public void trade() {

    }

    public void attack() {

    }

    public void surrender() {

    }

}
