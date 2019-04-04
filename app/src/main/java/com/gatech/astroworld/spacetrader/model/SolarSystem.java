package com.gatech.astroworld.spacetrader.model;

import android.graphics.Point;

import com.gatech.astroworld.spacetrader.entity.PoliticalSystems;
import com.gatech.astroworld.spacetrader.entity.Resources;
import com.gatech.astroworld.spacetrader.entity.TechLevel;
import com.gatech.astroworld.spacetrader.viewmodels.Galaxy_viewmodel;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SolarSystem {
    private String name;
    private List<Planet> listOfPlanets = new ArrayList<>();
    private TechLevel techLevel;
    private Resources resources;
    private SysLocation sysLocation;
    private int systemSize = 500;
    private int maxPlanets = 5;
    private int ssFuelMultipier = 3;
    private int maxPosX;
    private int maxPosY;

    private Random rand = new Random();
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
        this.name = solarSystemName[rand.nextInt(solarSystemName.length)];
        this.techLevel = TECHLEVEL_VALUES[rand.nextInt(TECHLEVEL_VALUES.length - 1)];
        this.resources = RESOURCES_VALUES[rand.nextInt(RESOURCES_VALUES.length - 1)];

        /* Compare new system distance from galactic center to the distance of every system created.
         * If the new system is within +/- systemMargin of an existing system, then the new system
         * distance is changed. */
        int sysListSize = Game.getInstance().getSystemList().size();
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

    public int getSystemSize() {
        return this.systemSize;
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

    public class SysLocation {
        private int galaxySize = Game.getInstance().getGalaxySize();
        private Point galaxyButtonSize = new Point(100, 100);
        private Random random = new Random();
        private double xPos;
        private double yPos;
        private double galacCenterDist;
        private int prevSize = 0;

//        SysLocation () {
//            this.xPos = (random.nextDouble() * 2 * systemMargin) - systemMargin;
//            this.yPos = (random.nextDouble() * 2 * systemMargin) - systemMargin;
//            galacCenterDist = Math.hypot(xPos, yPos);
//        }

        SysLocation (Point layoutSize) {
            final int numXintervals = 10;
            final int numYintervals = 10;
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
            this.xPos = (xRandPick) * unitXPixelDist - layoutSize.x/2.0;
            this.yPos = (yRandPick) * unitYPixelDist - layoutSize.y/2.0;

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
    public boolean equals(Object other) {
        if (this == other) return true;
        SolarSystem that = (SolarSystem) other;
        boolean sameLocation = this.getSysLocation() == that.getSysLocation();
        boolean sameName = this.getName().equals(that.getName());
        return sameLocation && sameName;
    }
    public String toString() {return getName();}

}
