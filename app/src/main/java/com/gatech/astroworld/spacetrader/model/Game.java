package com.gatech.astroworld.spacetrader.model;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.gatech.astroworld.spacetrader.entity.Difficulty;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Game {

    //Init game vars
    private Difficulty difficulty;
    private Player player;
    private List<SolarSystem> systemList = new ArrayList<>();
    private int galaxySize = 1000;

    // static variable single_instance of type Singleton
    private static Game single_instance;

    // private constructor restricted to this class itself
    private Game() {
        player = new Player("Default");
        difficulty = Difficulty.NORMAL;
    }

    // static method to create instance of Singleton class
    public static Game getInstance() {
        if (single_instance == null) {
            single_instance = new Game();
        }

        return single_instance;
    }

    public Player getPlayer() { return this.player; }

    public void setPlayer (Player player) { this.player = player; }

    public Difficulty getDifficulty () { return this.difficulty; }

    public void setDifficulty (Difficulty difficulty) { this.difficulty = difficulty; }

    public void setSystemList(List<SolarSystem> sysList) {
        systemList = sysList;
    }

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
        int maxSystems = 15;
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


    public void initializePlayerPlanet() {
        DisplayMetrics disp = Resources.getSystem().getDisplayMetrics();
        Player player = Game.getInstance().getPlayer();
        if (Game.getInstance().getDifficulty() == Difficulty.BEGINNER) {
            player.getShip().setFuel(10000);
            player.setCredits(2000);
            player.setReputation(200);
        }
        if (Game.getInstance().getDifficulty() == Difficulty.EASY) {
            player.getShip().setFuel(7000);
            player.setCredits(1600);
            player.setReputation(150);
        }
        if (Game.getInstance().getDifficulty() == Difficulty.HARD) {
            player.getShip().setFuel(3000);
            player.setCredits(750);
            player.setReputation(75);
        }
        if (Game.getInstance().getDifficulty() == Difficulty.IMPOSSIBLE) {
            player.getShip().setFuel(2000);
            player.setCredits(500);
            player.setReputation(50);
        }
        int width = disp.widthPixels;
        int height = disp.heightPixels;
        for (int i = 0; i < getMaxSystems(); i++) {
            SolarSystem sys = new SolarSystem(width , height);
            systemList.add(sys);
            System.out.println(sys.getSysLocation().getxPos());
            System.out.println(sys.getSysLocation().getyPos());
            for (Planet p: systemList.get(i).getListOfPlanets()) {
                System.out.println(p.getName());
            }
        }
        Random rand = new Random();
        int[] arr = {1, 10, 11, 12, 13, 14, 15, 2, 3, 4, 5,
                6, 7, 8, 9};


        int playerSystemIndex = (rand.nextInt(getSystemList().size()));
        player.setCurrentSystem(this.getSystemList().get(arr[playerSystemIndex]-1));
        player.setCurSystemReference(playerSystemIndex);

        int playerPlanetIndex = rand.nextInt(player.getCurrentSystem().
                getListOfPlanets().size());
        player.setCurrentPlanet(player.getCurrentSystem().getListOfPlanets().
                get(playerPlanetIndex));
        player.setCurPlanetReference(playerPlanetIndex);
    }




}
