package com.gatech.astroworld.spacetrader.model;

import android.graphics.Point;

public class ticTacToe {

    private byte choice;
    private byte[][] grid;
    private byte size = 0;

    int bet = 0;

    public ticTacToe(byte playerChoice) {
        //Player chooses to play X's or O's
        choice = playerChoice;
        generateBoard();
    }

    public void generateBoard() {
        //X = 1, O = 2
        grid = new byte[3][3];
    }

    public void addChoice(Point positon) {
        if (size < 9 && grid[positon.x][positon.y] == 0) {
            grid[positon.x][positon.y] = choice;
            size++;
        }
        if (winCheck()) {
            playerWin();
        }
        if (size >= 9 && !winCheck()) {
            playerLose();
        }
    }
    private boolean winCheck() {
        if (size < 3) {
            return false;
        }
        byte match = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == choice) {
                    match++;
                } else {
                    match = 0;
                }
                if (match == 3) {
                    return true;
                }
                if (grid[j][i] == choice) {
                    match++;
                } else {
                    match = 0;
                }
                if (match == 3) {
                    return true;
                }
                if (grid[j][j] == choice) {
                    match++;
                } else {
                    match = 0;
                }
                if (match == 3) {
                    return true;
                }
                if (grid[i][2 - i] == choice) {
                    match++;
                } else {
                    match = 0;
                }
                if (match == 3) {
                    return true;
                }
            }
        }
        return false;
    }

    private void playerWin() {
        System.out.println("YOU WON TIC TAC TOE");
    }

    private void playerLose() {
        System.out.println("YOU LOST TIC TAC TOE");
    }
}
