package com.gatech.astroworld.spacetrader.model;

import android.media.Image;

import com.gatech.astroworld.spacetrader.model.Planet;
import com.gatech.astroworld.spacetrader.model.SolarSystem;
import com.gatech.astroworld.spacetrader.model.Spaceship;

import java.util.ArrayList;

public class Player {
    private String name;
    private int skillPoints;
    private int pilotPoints, fighterPoints, traderPoints, engineerPoints;
    private int credits;
    private int reputation;
    private SolarSystem currentSystem;
    private Planet currentPlanet;
    private Spaceship ship;


    public Player(){
        this.skillPoints = 16;
        this.pilotPoints = 0;
        this.fighterPoints = 0;
        this.traderPoints = 0;
        this.engineerPoints = 0;
        this.credits = 1000;
        this.ship = new Spaceship("Gnat", 8, 500);
    }
    public Player(String name) {
        this();
        this.name = name;
    }

    public void setPilotPoints(int pilotPoints) {
        this.pilotPoints = pilotPoints;
    }

    public void setFighterPoints(int fighterPoints) {
        this.fighterPoints = fighterPoints;
    }

    public void setTraderPoints(int traderPoints) {
        this.traderPoints = traderPoints;
    }

    public void setEngineerPoints(int engineerPoints) {
        this.engineerPoints = engineerPoints;
    }

    public int getPilotPoints() {
        return pilotPoints;
    }

    public int getFighterPoints() {
        return fighterPoints;
    }

    public int getTraderPoints() {
        return traderPoints;
    }

    public int getEngineerPoints() {
        return engineerPoints;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public int getReputation() { return reputation; }

    public SolarSystem getCurrentSystem() { return currentSystem; }

    public Planet getCurrentPlanet() { return currentPlanet; }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public Spaceship getShip() {
        return ship;
    }

    public void setName(String nm) {
        name = nm;
    }

    public void setCredits(int c) {
        credits = c;
    }

    public void setShip(Spaceship s) {
        ship = s;
    }

    public void setReputation(int r) { reputation = r;}

    public void setCurrentSystem(SolarSystem ss) { currentSystem = ss; }

    public void setCurrentPlanet(Planet p) { currentPlanet = p; }

    /**
     *
     * @return a percent of encountering an NPC
     */
    private double encounterChance() {
        double chance = Math.random() / this.pilotPoints;
        return chance;
    }







    @Override
    public String toString(){
        return String.format(
                "\t\t* Name: %s " +
                "\n\t\t* Credits: %d " +
                "\n\t\t* Ship: %s " +
                "\n\t\t* Skill Points: " +
                "\n\t\t\t- Pilot Points:    %d" +
                "\n\t\t\t- Fighter Points:  %d" +
                "\n\t\t\t- Trader Points:   %d" +
                "\n\t\t\t- Engineer Points: %d" +
                "\nCurrent Planet: ",
                name, getCredits(), getShip().toString(), getPilotPoints(), getFighterPoints(), getTraderPoints(), getEngineerPoints(), currentPlanet.toString());
    }
}
