package com.example.myapplication.Model;

import com.example.myapplication.R;

public class Player {

    private static Player player;
    private float playerX;
    private float playerY;
    private int sprite;
    private int spritePicId;
    private int health;
    private int score;
    private String name;
    double difficulty;

    private Player() {
    }

    public static Player getInstance() {
        if (player == null) {
            player = new Player();
        }
        return player;
    }

    public void setSprite(int spriteNum) {
        sprite = spriteNum;
        if (sprite == R.id.Sprite1) { //Sets the sprite based on input
            spritePicId = R.drawable.sword_warrior; //Warrior - Sword
        } else if (sprite == R.id.Sprite2) {
            spritePicId = R.drawable.wizard_warrior; //Mage - Wizard
        } else if (sprite == R.id.Sprite3) {
            spritePicId = R.drawable.fighter_warrior; //Fighter - Fighter
        }
    }
    public int getSpritePicId() {
        return spritePicId;
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
    public void setScore(int score) {
        this.score = score;
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
    public void setPos(float x, float y) {
        playerX = x;
        playerY = y;
    }
    public float getX() {
        return playerX;
    }
    public float getY() {
        return playerY;
    }

}