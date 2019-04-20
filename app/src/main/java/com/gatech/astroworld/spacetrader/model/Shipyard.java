package com.gatech.astroworld.spacetrader.model;

public class Shipyard {

    private int fuelCount;
    private final int hundredFuelPrice = 50;


    public void incrementFuelCount() {
        fuelCount++;
    }

    public void decrementFuelCount() {
        if (fuelCount > -1) {
            fuelCount--;
        }
    }

    public int getFuelCount() {
        return  fuelCount;
    }

    public void setFuelCount(int c) {
        fuelCount = c;
    }

    public boolean buyFuel(int amount) {
        int playerCreds = Game.getInstance().getPlayer().getCredits();
        Spaceship ship = Game.getInstance().getPlayer().getShip();
        int fuelCreds = amount * hundredFuelPrice;
        if (fuelCreds <= playerCreds) {
            int added = 100 * amount;
            ship.setFuel(ship.getFuel() + added);
            Game.getInstance().getPlayer().
                    setCredits(playerCreds - fuelCreds);
            Save.saveCreditsInformation();
            Save.saveSpaceShipInformation();
            return true;
        }
        return false;

    }
}
