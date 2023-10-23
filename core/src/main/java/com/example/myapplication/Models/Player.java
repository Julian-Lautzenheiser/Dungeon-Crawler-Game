package com.example.myapplication.Models;

public class Player {

    private static Player player = null;
    private int playerX;
    private int playerY;
    private String sprite;
    private int health;
    private double score;
    private String name;
    private double difficulty;
    private float width;
    private float height;
    private int level;

    private Player() {
        this.playerX = 300;
        this.playerY = 100;
        this.sprite = "";
        this.health = 0;
        this.score = 200.0;
        this.name = "";
        this.difficulty = 0.0;
        this.level = 0;
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
    public int getPlayerX() {
        return playerX;
    }
    public int getPlayerY() {
        return playerY;
    }
    public void setPlayerX(int playerX) {
        player.playerX = playerX;
        if (player.playerX < 0) {
            player.playerX = 300;
        }
    }
    public void setPlayerY(int y) {
        player.playerY = playerY;
        if (player.playerY < 0) {
            player.playerY = 100;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void newScreen(int level) {
        this.level = level + 1;
        if (this.level > 3) {
            this.level = 0;
        }
    }
}
