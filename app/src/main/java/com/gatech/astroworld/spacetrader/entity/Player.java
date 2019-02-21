package com.gatech.astroworld.spacetrader.entity;

import com.gatech.astroworld.spacetrader.model.Spaceship;

import java.util.ArrayList;

public class Player {
    private String name;
    private int skillPoints;
    private int pilotPoints, fighterPoints, traderPoints, engineerPoints;
    private int credits;
    private Spaceship ship;

    public Player(String name){
        this.name = name;
        this.skillPoints = 16;
        this.pilotPoints = 0;
        this.fighterPoints = 0;
        this.traderPoints = 0;
        this.engineerPoints = 0;
        this.credits = 1000;
        this.ship = new Spaceship("Gnat", 8, 500);
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
                "\n\t\t\t- Engineer Points: %d",
                name, getCredits(), getShip().toString(), getPilotPoints(), getFighterPoints(), getTraderPoints(), getEngineerPoints());
    }
}
