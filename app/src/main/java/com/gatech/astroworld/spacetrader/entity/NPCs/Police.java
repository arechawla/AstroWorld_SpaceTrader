package com.gatech.astroworld.spacetrader.entity.NPCs;

public class Police {

    private int attack;
    private int lifePoints;
    private int bribeLevel;

    public Police(int attack, int lifePoints, int bribeLevel) {
        this.attack = attack;
        this.lifePoints = lifePoints;
        this.bribeLevel = bribeLevel;
    }

    //an NPC may interact w/ you, depending on your statistics
    public void interact() {
        //player loses items
        //player is fined 1.5 times the worth of the illegal items
        //if bribing is done then interaction is different
        // it takes some money and flees
    }

    //an NPC may avoid you, depending your statistics
    public void avoid() {
        //boolean ignore
        //true if it ignores you false otherwise
    }

    //and NPC may attack you, dependent on your stats
    public void attack() {
        //if wanted level is high enough, they will attack you
    }


}
