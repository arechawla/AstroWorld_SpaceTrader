package com.gatech.astroworld.spacetrader.model;

import android.graphics.Point;

import com.gatech.astroworld.spacetrader.entity.GoodType;
import com.gatech.astroworld.spacetrader.model.Goods.TradeGood;

import java.util.ArrayList;
import java.util.List;

public class Spaceship {

    private String name;
    private List<TradeGood> cargo;
    private int capacity;
    private double fuel;
    private int ssFuelMultiplier = 3;
    private int unitFuelUse = 50;


    public Spaceship (String name, int capacity, int fuel) {
        this.name = name;
        this.cargo = new ArrayList<>();
        this.capacity = capacity;
        this.fuel = fuel;
    }

    public List<TradeGood> getCargoList() {
        return cargo;
    }

    public int cargoAmount() {
        int total = 0;
        for (TradeGood item: cargo) {
            total += item.getQuantity();
        }
        return total;
    }

    public void zeroSellCounts() {
        for (TradeGood tGood: cargo) {
            tGood.setSellCount(0);
        }
    }

    public Integer containsCargo(TradeGood find) {
        for (int i = 0; i < cargo.size(); i++) {
            if (find.getName().equals(cargo.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }

    public double travelSolarSystem(SolarSystem current,
                                      SolarSystem travelTo, Point layoutSize) {
        int numXintervals = 10;
        int numYintervals = 10;

        int unitXPixelDist = layoutSize.x/numXintervals;
        int unitYPixelDist = layoutSize.y/numYintervals;

        if (current.getName().equals(travelTo.getName())) {
            return 0;
        }

        double xDist = Math.abs(travelTo.getSysLocation().getxPos() -
                current.getSysLocation().getxPos());
        int deltaX = (int)xDist/unitXPixelDist;

        double yDist = Math.abs(travelTo.getSysLocation().getyPos() -
                current.getSysLocation().getyPos());
        int deltaY = (int)yDist/unitYPixelDist;

        double dist = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        double fuelUsed = dist * unitFuelUse
                * ssFuelMultiplier;
        if (fuelUsed > fuel) {
            return -1;
        } else {
            return fuelUsed;
        }
    }


    public double travelPlanet(Player p, Planet travelTo, Point layoutSize) {
        int numXintervals = 10;
        int numYintervals = 10;

        int unitXPixelDist = layoutSize.x/numXintervals;
        int unitYPixelDist = layoutSize.y/numYintervals;
        double xDist = 0;
        double yDist = 0;
        Planet curr = p.getCurrentPlanet();
        if (curr == null) {
            xDist = Math.abs(travelTo.getPlanLocation().getxPos() -
                    0);
            yDist = Math.abs(travelTo.getPlanLocation().getyPos() -
                    0);
        } else {
            if (travelTo.getName().equals(curr.getName())) {
                return 0;
            }
            xDist = Math.abs(travelTo.getPlanLocation().getxPos() -
                    curr.getPlanLocation().getxPos());
            yDist = Math.abs(travelTo.getPlanLocation().getyPos() -
                    curr.getPlanLocation().getxPos());
        }


        int deltaX = (int)xDist/unitXPixelDist;


        int deltaY = (int)yDist/unitYPixelDist;

        double dist = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
        double fuelUsed = dist * unitFuelUse;
        if (fuelUsed > fuel) {
            return -1;
        } else {
            return fuelUsed;
        }
    }

    public int getSpaceLeft() {
        return capacity - cargoAmount();
    }

    public double getFuel() { return fuel; }

    public void setFuel(double f) {
        fuel = f;
    }


    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return name;
    }



}
