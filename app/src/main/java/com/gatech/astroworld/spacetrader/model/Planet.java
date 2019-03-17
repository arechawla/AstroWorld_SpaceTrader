package com.gatech.astroworld.spacetrader.model;

import com.gatech.astroworld.spacetrader.entity.PoliticalSystems;

import java.util.Random;

public class Planet {
    private String name;
    private PlanetLocation loc;
    private PoliticalSystems gov;
    private SolarSystem sys;
    private Store store;
    private Player player;

     public Planet(SolarSystem sys) {
        this.sys = sys;
        this.store = null;
//        this.store = new Store( 3000, sys, this);
        name = "Planet "+ sys.getListOfPlanets().size();
    }

    public void setSys(SolarSystem system) {
        sys = system;
    }

    public void setStore(Store s) {
        store = s;

    }

    public String getName() {
        return name;
    }

    public PlanetLocation getLoc() {
        return loc;
    }

    public PoliticalSystems getGov() {
        return gov;
    }

    public void setName(String n) {
        name = n;
    }

    public void setLoc(PlanetLocation location) {
        loc = location;
    }

    public void setGov(PoliticalSystems pol) {
        gov = pol;
    }

    public class PlanetLocation {
        private int systemSize = sys.getSystemSize();
        Random random = new Random();
        private double xPos;
        private double yPos;
        private double systemCenterDist;

        PlanetLocation () {
            this.xPos = (random.nextDouble() * 2 * systemSize) - systemSize;
            this.yPos = (random.nextDouble() * 2 * systemSize) - systemSize;
            systemCenterDist = Math.hypot(xPos, yPos);
        }

        public double getSystemCenterDist() {
            return systemCenterDist;
        }
        public void updateLocation(double newX, double newY){
            this.xPos = newX;
            this.yPos = newY;
            systemCenterDist = Math.hypot(xPos, yPos);
        }
        public void randomLocation() {
            updateLocation((random.nextDouble() * 2 * systemSize) - systemSize,
                    (random.nextDouble() * 2 * systemSize) - systemSize);
        }

        public double getxPos() {
            return xPos;
        }

        public double getyPos() {
            return yPos;
        }

    }

    @Override
    public String toString() {
        return getName();
    }

    public Store getStore() { return store; }
}
