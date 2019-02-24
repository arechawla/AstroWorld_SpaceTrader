package com.gatech.astroworld.spacetrader.model;

import com.gatech.astroworld.spacetrader.entity.PoliticalSystems;

public class Planet {
    private String name;
    private SolarSystem.SysLocation loc;
    private PoliticalSystems gov;

    public Planet(String name, SolarSystem.SysLocation loc, PoliticalSystems gov) {
        this.name = name;
        this.loc = loc;
        this.gov = gov;
    }

    public String getName() {
        return name;
    }

    public SolarSystem.SysLocation getLoc() {
        return loc;
    }

    public PoliticalSystems getGov() {
        return gov;
    }

    public void setName(String n) {
        name = n;
    }

    public void setLoc(SolarSystem.SysLocation location) {
        loc = location;
    }

    public void setGov(PoliticalSystems pol) {
        gov = pol;
    }


}
