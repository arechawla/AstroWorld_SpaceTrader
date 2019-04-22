package com.gatech.astroworld.spacetrader.model;


import com.gatech.astroworld.spacetrader.model.Planet;
import com.gatech.astroworld.spacetrader.model.SolarSystem;
import com.gatech.astroworld.spacetrader.model.Spaceship;

import com.gatech.astroworld.spacetrader.entity.NPCs.Pirate;
import com.gatech.astroworld.spacetrader.entity.NPCs.Trader;
import com.gatech.astroworld.spacetrader.entity.NPCs.Police;


import java.util.ArrayList;

import com.gatech.astroworld.spacetrader.model.Goods.TradeGood;


public class Player {
    private String name;
    private int skillPoints;
    private int pilotPoints;
    private int fighterPoints;
    private int traderPoints;
    private int engineerPoints;
    private int credits;
    private int reputation;
    private SolarSystem currentSystem;
    private Planet currentPlanet;
    private Spaceship ship;
    private boolean surrenderVar = false;


    //for dataBase
    private int curSystemReference;
    private  int curPlanetReference;


    public Player(){
        this.name = "Default";
        this.skillPoints = 16;
        this.pilotPoints = 0;
        this.fighterPoints = 0;
        this.traderPoints = 0;
        this.engineerPoints = 0;
        this.credits = 1000;
        this.reputation = 100;
        this.ship = new Spaceship("Gnat", 25, 5000);
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

    public int getCurSystemReference() { return curSystemReference; }

    public void setCurSystemReference(int c) {
        curSystemReference = c;
    }

    public int getCurPlanetReference() { return curPlanetReference; }

    public void setCurPlanetReference(int c) {
        curPlanetReference = c;
    }

    public void changeCargoPrices(Planet p) {
        for (TradeGood item: Game.getInstance().getPlayer().getShip().getCargoList()) {
            item.setPlanet(p);
            item.setSys(p.getSys());
            item.setPrice(item.calculatePrice());
        }
    }

    /**
     *
     * @return a percent of encountering an NPC
     */
    private double encounterChance() {
        double chance = Math.random() / this.pilotPoints;
        return chance;
    }


    //NPC METHOD INTERACTIONS START HERE
    public void attack() {

    }

    public void surrender() {

    }


    @Override
    public String toString(){
        return name;
    }
}
