package com.gatech.astroworld.spacetrader.model;

public enum Spaceship {

    GNAT("Gnat");

    private final String code;

    Spaceship(String code) {this.code = code;}

    @Override
    public String toString() {
        return code;
    }
}
