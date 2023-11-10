package com.example.myapplication.Models;

public interface Enemy {
    void move();
    int attack();
    void damageTaken();
    float getPositionX();
    float getPositionY();
    void setPositionY(float yCoordinate);
    void setPositionX(float xCoordinate);
    String chosenDifficulty(double difficulty);
}
