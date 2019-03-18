package com.gatech.astroworld.spacetrader.entity.NPCs;

import com.gatech.astroworld.spacetrader.entity.EncounterNPC;

public class Pirate implements EncounterNPC {

    private int attack;
    private int lifePoints;

    public Pirate(int attack, int lifePoints) {
        this.attack = attack;
        this.lifePoints = lifePoints;
    }
    // these parameters can be randomized upwards
    // as player has more credits in the game

    //an NPC may interact w/ you, depending on your statistics
    public void interact() {
        //pirate ship gives you ton of money
    }

    //an NPC may avoid you, depending your statistics
    public void avoid() {
        //boolean ignore
        //true if it ignores you false otherwise
    }

    //and NPC may attack you, dependent on your stats
    public void attack() {
        //detract from your ship's health
    }

}
