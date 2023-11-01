package com.example.myapplication.Models;

import com.badlogic.gdx.math.Vector2;

public class Player {

    private static Player player = null;
    private Vector2 position;
    private Vector2 velocity;
    private String sprite;
    private int health;
    private double score;
    private String name;
    private double difficulty;
    private float width;
    private float height;
    private final float maxVelocity = 10f;

    private Player() {
        position = new Vector2(256, 128);
        velocity = new Vector2(0, 0);
        this.sprite = "";
        this.health = 0;
        this.score = 200.0;
        this.name = "";
        this.difficulty = 0.0;
    }

    public static Player getInstance() {
        if (player == null) {
            synchronized (Player.class) {
                if (player == null) {
                    player = new Player();
                }
            }
        }
        return player;
    }

    public String getSprite() {
        return sprite;
    }
    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public int getHealth() {
        return health;
    }
    public String getName() {
        return name;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }
    public double getDifficulty() {
        return difficulty;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getPlayerX() {
        return position.x;
    }
    public float getPlayerY() {
        return position.y;
    }

    public Vector2 getPosition() {
        return position;
    }
    public Vector2 getVelocity() {
        return velocity;
    }

    public void setPlayerX(float playerX) {
        position.x = playerX;
        if (position.x < 0) {
            position.x = 300;
        }
    }
    public void setPlayerY(float playerY) {
        position.y = playerY;
        if (position.y < 0) {
            position.y = 100;
        }
    }

    public double getScore() {
        return this.score;
    }
  
    public void setScore(double newScore) {
        if (newScore >= 0) {
            this.score = newScore;
        }
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getMaxVelocity() {
        return maxVelocity;
    }
}
