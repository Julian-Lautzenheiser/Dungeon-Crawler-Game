
package com.example.myapplication.Models;

import com.badlogic.gdx.math.Vector2;

public class Player implements PlayerInterface {

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
    private int damage;
    private boolean attacking;

    private Player() {
        this.position = new Vector2(62, 182);
        this.velocity = new Vector2(0, 0);
        this.sprite = "";
        this.health = 200;
        this.score = 0.0;
        this.name = "";
        this.difficulty = 0.0;
        this.level = 0;
        this.damage = 15;
        this.attacking = false;
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
    public void resetPlayer() {
        this.health = 200;
        this.score = 0.0;
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
        return this.health;
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
            position.x = 62;
        }
    }
    public void setPlayerY(float playerY) {
        position.y = playerY;
        if (position.y < 0) {
            position.y = 182;
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

    public void addScore(double points) {
        if (score + points >= 0) {
            score += points;
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
        this.health -= damage;
    }
    public boolean isAttacking() {
        return attacking;
    }
    public void setAttacking(boolean isAttacking) {
        attacking = isAttacking;
    }
}
