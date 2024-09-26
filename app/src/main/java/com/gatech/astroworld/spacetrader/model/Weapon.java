package com.gatech.astroworld.spacetrader.model;

public class Weapon {
    private int power; //scale of 1-20
    private int health;

    public Weapon(int power) {
        this.power = power;
        health = 100;
    }

    public void setPower(int p) {
        power = p;
    }

    public int getPower() {
        return power;
    }
}
