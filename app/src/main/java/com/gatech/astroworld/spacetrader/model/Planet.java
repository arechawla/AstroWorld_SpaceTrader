package com.gatech.astroworld.spacetrader.model;

import android.graphics.Point;

import com.gatech.astroworld.spacetrader.entity.PoliticalSystems;

import java.util.Random;

public class Planet {
    private String name;
    private PlanetLocation planetLocation;
    private PoliticalSystems gov;
    private SolarSystem sys;
    private Store store;
    private Player player;
    private Random rand = new Random();

     public Planet(SolarSystem sys) {
        this.sys = sys;
        this.store = null;
//        this.store = new Store( 3000, sys, this);
//        name = "Planet "+ sys.getListOfPlanets().size();

         String[] planetName = {
                 "Mercury",
                 "Venus",
                 "Earth",
                 "Mars",
                 "Jupiter",
                 "Saturn",
                 "Neptune",
                 "Pluto",
                 "Astro",
                 "TravisScott"
         };
         this.name = planetName[rand.nextInt(planetName.length)];

         this.planetLocation = new Planet.PlanetLocation
                 (new Point(sys.getMaxPosX(), sys.getMaxPosY()));
    }

    public void setSys(SolarSystem system) {
        sys = system;
    }

    public void setStore(Store s) {
        store = s;

    }

    public Planet.PlanetLocation getPlanLocation() {
        return planetLocation;
    }

    public String getName() {
        return name;
    }

    public PlanetLocation getLoc() {
        return planetLocation;
    }

    public PoliticalSystems getGov() {
        return gov;
    }

    public void setName(String n) {
        name = n;
    }

    public void setLoc(PlanetLocation location) {
        planetLocation = location;
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

//        PlanetLocation () {
//            this.xPos = (random.nextDouble() * 2 * systemSize) - systemSize;
//            this.yPos = (random.nextDouble() * 2 * systemSize) - systemSize;
//            systemCenterDist = Math.hypot(xPos, yPos);
//        }


        PlanetLocation (Point layoutSize) {
            int numXintervals = 10;
            int numYintervals = 10;
            int[][] grid = new int[numXintervals][numYintervals];
            int unitXPixelDist = layoutSize.x/numXintervals;
            int unitYPixelDist = layoutSize.y/numYintervals;

            int xRandPick = random.nextInt(numXintervals);
            int yRandPick = random.nextInt(numYintervals);
            while (grid[xRandPick][yRandPick] == 1) {
                xRandPick = random.nextInt(numXintervals);
                yRandPick = random.nextInt(numYintervals);
            }
            grid[xRandPick][yRandPick] = 1;
            this.xPos = (xRandPick) * unitXPixelDist - layoutSize.x/2;
            this.yPos = (yRandPick) * unitYPixelDist - layoutSize.y/2;

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
