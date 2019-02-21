package com.gatech.astroworld.spacetrader.entity;

import com.gatech.astroworld.spacetrader.model.Spaceship;

public interface EncounterNPC {

    //an NPC may interact w/ you, depending on your statistics
    void interact();

    //an NPC may avoid you, depending your statistics
    void avoid();

    //and NPC may attack you, dependent on your stats
    void attack();

    //the player may surrender to the NPC
    void surrenderToNPC();
}
