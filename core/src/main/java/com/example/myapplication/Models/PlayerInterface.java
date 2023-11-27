package com.example.myapplication.Models;

import com.badlogic.gdx.math.Vector2;

public interface PlayerInterface {
    default void play() {
        return;
    }
    void resetPlayer();

    String getSprite();

    void setSprite(String sprite);

    void setHealth(int health);

    int getHealth();

    String getName();

    void setDifficulty(double difficulty);

    double getDifficulty();

    void setName(String name);

    float getPlayerX();

    float getPlayerY();

    Vector2 getPosition();

    Vector2 getVelocity();

    void setPlayerX(float playerX);

    void setPlayerY(float playerY);

    double getScore();

    void setScore(double newScore);

    float getHeight();

    float getWidth();

    void setHeight(float height);

    void setWidth(float width);

    int getLevel();

    void setLevel(int level);

    void newScreen(int level);

    float getMaxVelocity();

    void damageTaken(int damage);

    int getDamage();
}
