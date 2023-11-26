package com.example.myapplication.Models;

public interface Enemy {
    void move(String level);
    int attack();
    void damageTaken();
    boolean getAlive();
    float getPositionX();
    float getPositionY();
    int getWidth();
    int getHeight();
    void setPositionY(float yCoordinate);
    void setPositionX(float xCoordinate);
    String chosenDifficulty(double difficulty);
    void setWidth(int width);
    void setHeight(int height);
}
