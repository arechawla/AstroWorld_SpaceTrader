package com.gatech.astroworld.spacetrader.model;

import android.graphics.Point;

import com.gatech.astroworld.spacetrader.entity.PoliticalSystems;
import com.gatech.astroworld.spacetrader.entity.Resources;
import com.gatech.astroworld.spacetrader.entity.TechLevel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SolarSystem {
    private String name;
    private List<Planet> listOfPlanets = new ArrayList<>();
    private TechLevel techLevel;
    private SysLocation sysLocation;

    private int maxPlanets = 5;
    private int maxPosX;
    private int maxPosY;

    public SolarSystem (int maxPosX, int maxPosY) {

        final TechLevel[] TECHLEVEL_VALUES = TechLevel.values();
        final Resources[] RESOURCES_VALUES = Resources.values();
        this.maxPosX = maxPosX;
        this.maxPosY = maxPosY;
        String[] solarSystemName = {
                "Acamar",
                "Adahn",
                "Aldea",
                "Andevian",
                "Antedi",
                "Balosnee",
                "Baratas",
                "Brax",
                "Bretel",
                "Calondia",
                "Campor",
                "Capelle",
                "Carzon",
                "Castor",
                "Cestus",
                "Cheron",
                "Courteney",
                "Daled",
                "Damast",
                "Davlos",
                "Deneb",
                "Deneva",
                "Devidia",
                "Draylon",
                "Drema",
                "Endor",
                "Esmee",
                "Exo",
                "Ferris",
                "Festen",
                "Fourmi",
                "Frolix",
                "Gemulon",
                "Guinifer",
                "Hades",
                "Hamlet",
                "Helena",
                "Hulst",
                "Iodine",
                "Iralius",
                "Janus",
                "Japori",
                "Jarada",
                "Jason",
                "Kaylon",
                "Khefka",
                "Kira",
                "Klaatu",
                "Klaestron",
                "Korma",
                "Kravat",
                "Krios",
                "Laertes",
                "Largo",
                "Lave",
                "Ligon",
                "Lowry",
                "Magrat",
                "Malcoria",
                "Melina",
                "Mentar",
                "Merik",
                "Mintaka",
                "Montor",
                "Mordan",
                "Myrthe",
                "Nelvana",
                "Nix",
                "Nyle",
                "Odet",
                "Og",
                "Omega",
                "Omphalos",
                "Orias",
                "Othello",
                "Parade",
                "Penthara",
                "Picard",
                "Pollux",
                "Quator",
                "Rakhar",
                "Ran",
                "Regulas",
                "Relva",
                "Rhymus",
                "Rochani",
                "Rubicum",
                "Rutia",
                "Sarpeidon",
                "Sefalla",
                "Seltrice",
                "Sigma",
                "Sol",
                "Somari",
                "Stakoron",
                "Styris",
                "Talani",
                "Tamus",
                "Tantalos",
                "Tanuga",
                "Tarchannen",
                "Terosa",
                "Thera",
                "Titan",
                "Torin",
                "Triacus",
                "Turkana",
                "Tyrus",
                "Umberlee",
                "Utopia",
                "Vadera",
                "Vagra",
                "Vandor",
                "Ventax",
                "Xenon",
                "Xerxes",
                "Yew",
                "Yojimbo",
                "Zalkon",
                "Zuul"
        };
        Random rand = new Random();
        this.name = solarSystemName[rand.nextInt(solarSystemName.length)];
        this.techLevel = TECHLEVEL_VALUES[rand.nextInt(TECHLEVEL_VALUES.length - 1)];
//        this.resources = RESOURCES_VALUES[rand.nextInt(RESOURCES_VALUES.length - 1)];

        /* Compare new system distance from galactic center to the distance of every system created.
         * If the new system is within +/- systemMargin of an existing system, then the new system
         * distance is changed. */
        this.sysLocation = new SysLocation(new Point(maxPosX, maxPosY));
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
                "Acworth",
                "Skiles"
        };

        int[] tracker = new int[planetName.length];
        for (int i = 0; i < maxPlanets; i++) {
            Planet p = new Planet(this); //Create new planet
            int nameInd = rand.nextInt(planetName.length); //randomly get name
            while (tracker[nameInd] == 1) {
                nameInd = rand.nextInt(planetName.length);
            }
            tracker[nameInd] = 1;
            p.setName(planetName[nameInd]);
            addPlanet(p);
        }


    }

    public int getMaxPlanets() {
        return maxPlanets;
    }
    public SysLocation getSysLocation() {
        return sysLocation;
    }

    public int getMaxPosX() {
        return maxPosX;
    }

    public int getMaxPosY() {
        return maxPosY;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTechLevel(TechLevel tech) {
        this.techLevel = tech;
    }

    public void setListOfPlanets(List<Planet> planList) {
        listOfPlanets = planList;
    }


    public List<Planet> getListOfPlanets() {
        return listOfPlanets;
    }

    public void addPlanet(Planet newPlanet) {
        this.listOfPlanets.add(newPlanet);
        Random rand = new Random();
        PoliticalSystems[] arr = PoliticalSystems.values();
        PoliticalSystems g = arr[rand.nextInt(PoliticalSystems.values().length - 1)];
        newPlanet.setGov(g);
    }

    public String getName() {
        return name;
    }

    public TechLevel getTechLevel() {
        return techLevel;
    }


    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        SolarSystem that = (SolarSystem) other;
        boolean sameLocation = this.getSysLocation() == that.getSysLocation();
        boolean sameName = this.getName().equals(that.getName());
        return sameLocation && sameName;
    }
    public String toString() {return getName();}

}
