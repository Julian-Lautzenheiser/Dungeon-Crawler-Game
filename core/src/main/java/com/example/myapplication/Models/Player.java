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
    private int level;
    private final float maxVelocity = 10f;
<<<<<<< HEAD
    private int level;
=======
    private int damage;
>>>>>>> 94de97941abcd67b434dcf2131d9b4ca86104576

    private Player() {
        this.position = new Vector2(62, 182);
        this.velocity = new Vector2(0,0);
        this.sprite = "";
        this.health = 200;
        this.score = 0.0;
        this.name = "";
        this.difficulty = 0.0;
        this.level = 0;
        this.damage = 15;
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
            position.x = 50;
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
        return this.height;
    }

    public float getWidth() {
        return this.width;
    }

    public void setHeight(float height) {
        if (height > 0) {
            this.height = height;
        } else {
            this.height = 5;
        }
    }

    public void setWidth(float width) {
        if (width > 0) {
            this.width = width;
        } else {
            this.width = 5;
        }
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
    
    public float getMaxVelocity() {
        return this.maxVelocity;
    }
    
    public void damageTaken(int damage) {
        if (this.health > 0) {
            this.health -= damage;
        }
    }
    
    public int getDamage() {
        return this.damage;
    }
}
