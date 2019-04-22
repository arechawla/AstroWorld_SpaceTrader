package com.gatech.astroworld.spacetrader.model;

import android.graphics.Point;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlanetTest {

    SolarSystem system;
    SolarSystem system2;
    Planet planetFirst;
    Planet planetSecond;
    //Store testStore;

    @Before
    public void setUp() {
        system = new SolarSystem(100, 100);
        system2 = new SolarSystem(100, 100);
        planetFirst = new Planet(system);
        planetSecond = new Planet(system2);
        //testStore = new Store(1000, planet);
    }

    @Test
    public void testEquals() {
        PlanetLocation planetLocation = new PlanetLocation
                (new Point(system.getMaxPosX(), system.getMaxPosY()));

        planetFirst.setName("Same");
        planetSecond.setName("Same");
        planetFirst.setSys(planetSecond.getSys());
        assertFalse("Planet positions should not be equal", planetFirst.equals(planetSecond));

        //Now make name different
        planetFirst.setName("QWERTY");
        planetSecond.setName("YTREWQ");
        planetFirst.setLoc(planetLocation);
        planetSecond.setLoc(planetLocation);
        planetFirst.setSys(system);
        planetFirst.setSys(system);
        assertFalse("Planet names should not be equal here", planetFirst.equals(planetSecond));

        //Now make systems different
        planetFirst.setSys(system);
        planetSecond.setSys(system2);
        assertFalse("Planet systems should not be equal", planetFirst.equals(planetSecond));

        //Now set fields to the same

        planetFirst.setPlanetLocation(planetLocation);
        planetSecond.setPlanetLocation(planetLocation);
        planetFirst.setName("Same");
        planetSecond.setName("Same");
        planetFirst.setSys(system);
        planetSecond.setSys(system);
        assertTrue("Planets should be equal here", planetFirst.equals(planetSecond));
    }

}