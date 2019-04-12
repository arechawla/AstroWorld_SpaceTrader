package com.gatech.astroworld.spacetrader.model;

import android.graphics.Point;

import java.util.Random;

public class PlanetLocation {

    private double xPos;
    private double yPos;


    PlanetLocation (Point layoutSize) {
        Random random = new Random();
        int numXintervals = 10;
        int numYintervals = 10;
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
        this.xPos = (xRandPick * unitXPixelDist) - layoutSize.x/2.0;
        this.yPos = (yRandPick * unitYPixelDist) - layoutSize.y/2.0;

    }


    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setxPos(double x) {
        this.xPos = x;
    }

    public void setyPos(double y) {
        this.yPos = y;
    }

}
