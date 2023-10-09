package com.example.myapplication.Model;

import androidx.constraintlayout.widget.ConstraintLayout;

public class GameModel {

    private double difficulty;
    private int screenWidth;
    private int screenHeight;
    private final int playerTextOffset = 100;
    private final int healthBase = 100;
    private final int maxSize = 300;

    public GameModel() {

    }

    public void setScreenDimensions(int width, int height) {
        screenWidth = width;
        screenHeight = height;
    }
    public int getMaxSize() {
        return maxSize;
    }
    public int getHealthBase() {
        return healthBase;
    }
    public int getPlayerTextOffset() {
        return playerTextOffset;
    }
}
