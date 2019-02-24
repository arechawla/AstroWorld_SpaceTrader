package com.gatech.astroworld.spacetrader.model;


import com.gatech.astroworld.spacetrader.entity.Resources;
import com.gatech.astroworld.spacetrader.entity.TechLevel;

import java.util.List;
import java.util.Random;

public class SolarSystem {
    private String name;
    private List<Planet> listOfPlanets;
    private TechLevel techLevel;
    private Resources resources;
    private SysLocation sysLocation;
    private int systemSize = 1000;

    private int systemMargin = 5;

    public SolarSystem (String name, TechLevel techLevel, Resources resources) {
        SysLocation loc = new SysLocation();
        this.name = name;
        this.resources = resources;
        this.techLevel = techLevel;
        this.sysLocation = loc;

        /* Compare new system distance from galactic center to the distance of every system created.
         * If the new system is within +/- systemMargin of an existing system, then the new system
         * distance is changed. */
        for (SolarSystem tempSys: Game.getInstance().getSystemList()) {
            double tempX = tempSys.sysLocation.xPos;
            double tempY = tempSys.sysLocation.yPos;
            double distance = Math.sqrt(Math.pow((sysLocation.xPos - tempX), 2)
                            + Math.pow((sysLocation.yPos - tempY), 2));

            while (distance < systemMargin) {
                sysLocation.randomLocation();
                distance = Math.sqrt(Math.pow((sysLocation.xPos - tempX), 2)
                        + Math.pow((sysLocation.yPos - tempY), 2));
            }
        }
    }

    public int getSystemSize() {
        return this.systemSize;
    }

    public List<Planet> getListOfPlanets() {
        return listOfPlanets;
    }

    public void addPlanet(Planet newPlanet) {
        this.listOfPlanets.add(newPlanet);
    }

    public String getName() {
        return name;
    }

    public TechLevel getTechLevel() {
        return techLevel;
    }

    public class SysLocation {
        private int galaxySize = Game.getInstance().getGalaxySize();
        Random random = new Random();
        private double xPos;
        private double yPos;
        private double galacCenterDist;

        SysLocation () {
            this.xPos = (random.nextDouble() * 2 * galaxySize) - galaxySize;
            this.yPos = (random.nextDouble() * 2 * galaxySize) - galaxySize;
            galacCenterDist = Math.hypot(xPos, yPos);
        }

        public double getGalacCenterDist() {
            return galacCenterDist;
        }
        public void updateLocation(double newX, double newY){
            this.xPos = newX;
            this.yPos = newY;
            galacCenterDist = Math.hypot(xPos, yPos);
        }
        public void randomLocation() {
            updateLocation((random.nextDouble() * 2 * galaxySize) - galaxySize,
                            (random.nextDouble() * 2 * galaxySize) - galaxySize);
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
        /*return String.format("Name: %s" +
                "\nPlanets: %d" +
                "\nLocation:  " +
                "\n\tXpos = %d" +
                "\n\tYpos = %d" +
                "\nTech Level: %s" +
                "\nResources:" +
                "\n\t%s",
                (double)5, sysLocation.getxPos(), sysLocation.getyPos(), getTechLevel().toString(), resources.toString());
                */
        return ("X LOCATION = " + this.sysLocation.getxPos() + " Y LOCATION = " + this.sysLocation.getyPos());
    }

}
