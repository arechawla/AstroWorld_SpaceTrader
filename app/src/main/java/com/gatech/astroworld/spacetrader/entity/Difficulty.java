package com.gatech.astroworld.spacetrader.entity;

public enum Difficulty {
    BEGINNER("Beginner"),
    EASY("Easy"),
    NORMAL("Normal"),
    HARD("Hard"),
    IMPOSSIBLE("Impossible");

    private final String code;

    Difficulty(String code) {this.code = code;}

    @Override
    public String toString() {
        return code;
    }

}
