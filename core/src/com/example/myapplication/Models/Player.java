package com.example.myapplication.Models;

public class Player {

    private static Player player = null;
    private int X;
    private int Y;
    private String sprite;
    private int health;
    private double score;
    private String name;
    private double difficulty;

    private Player() {
        this.X = 300;
        this.Y = 100;
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
    public int getX() {
        return X;
    }
    public int getY() {
        return Y;
    }
    public void setX(int x) {
        player.X = x;
        if(player.X < 0) {
            player.X = 300;
        }
    }
    public void setY(int y) {

        player.Y = y;
        if(player.Y < 0) {
            player.Y = 100;
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
}
