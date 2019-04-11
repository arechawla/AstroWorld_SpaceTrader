package com.gatech.astroworld.spacetrader.model;

import android.graphics.Point;
import com.gatech.astroworld.spacetrader.entity.PoliticalSystems;


public class Planet {
    private String name;
    private PlanetLocation planetLocation;
    private PoliticalSystems gov;

    private SolarSystem sys;
    private Store store;

     public Planet(SolarSystem sys) {
        this.sys = sys;
        this.store = new Store(3000, this);
        this.planetLocation = new PlanetLocation
                 (new Point(sys.getMaxPosX(), sys.getMaxPosY()));
    }



    public SolarSystem getSys() {
         return sys;
    }

    public PlanetLocation getPlanLocation() {
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



    @Override
    public String toString() {
        return getName();
    }

    public Store getStore() { return store; }

    @Override
    public boolean equals(Object other) {
         if (this == other) return true;
         Planet that = (Planet) other;
         boolean sameLocation = this.getPlanLocation() == that.getPlanLocation();
         boolean sameName = this.getName().equals(that.getName());
         return sameLocation && sameName;
    }
}
