package com.gatech.astroworld.spacetrader.model;

import com.gatech.astroworld.spacetrader.entity.Difficulty;

import java.util.ArrayList;
import java.util.List;

public class Game {

    //Init game vars
    private Difficulty difficulty;
    private Player player;
    private List<SolarSystem> systemList = new ArrayList<>();
    private int galaxySize = 1000;
    private int maxSystems = (15) ;

    // static variable single_instance of type Singleton
    private static Game single_instance = null;

    // private constructor restricted to this class itself
    private Game() {
        player = new Player(null);
        difficulty = Difficulty.BEGINNER;

    }

    // static method to create instance of Singleton class
    public static Game getInstance() {
        if (single_instance == null)
            single_instance = new Game();

        return single_instance;
    }

    public Player getPlayer() { return this.player; }

    public void setPlayer (Player player) { this.player = player; }

    public Difficulty getDifficulty () { return this.difficulty; }

    public void setDifficulty (Difficulty difficulty) { this.difficulty = difficulty; }

    public List<SolarSystem> getSystemList() {
        return systemList;
    }
    public void addSystem(SolarSystem newSystem) {
        systemList.add(newSystem);
    }

    public int getGalaxySize() {
        return this.galaxySize;
    }

    public void setGalaxySize(int newSize) {
        this.galaxySize = newSize;
    }

    public int getMaxSystems() {
        return maxSystems;
    }
    @Override
    public String toString () {
        /* return "\n********************************************************"
        + "\nSettings: \n"
        + "\n********************************************************"
        + "\nPlayer: "
        + "\n" + player.toString() + " "
        + "\n********************************************************"
        + "\nGame: "
        + "\n\t\t* Difficulty: " + this.getDifficulty().toString()
        + "\n********************************************************"; */
        return "Game toString test";
    }
}
