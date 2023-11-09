package com.example.myapplication.Models;

import com.badlogic.gdx.math.Vector2;
public class OgreEnemy implements Enemy {
    // Implement attributes and behaviors for the Ogre
    // e.g., sprite, speed, size
    private Vector2 velocity;
    private Vector2 position;
    private int damage;
    private int health;
    Player player = Player.getInstance();
    
    public OgreEnemy() {
        this.velocity = new Vector2(15, 20);
        this.position = new Vector2(0,0);
        this.damage = 15;
        this.health = 175;
    }
    @Override
    public void move() {
        // Implement movement logic
    }

    @Override
    public int attack() {
        switch(chosenDifficulty(player.getDifficulty())) {
            case "Easy":
                this.damage = 8;
                break;
            case "Medium":
                this.damage = 16;
                break;
            case "Hard":
                this.damage = 24;
                break;
        }
        return this.damage;
    }

    @Override
    public void damageTaken() {
        //Implement hp logic
        if (this.health > 0) {
            this.health -= player.getDamage();
        }
    }

    @Override
    public float getPositionX() {
        return this.position.x;
    }
    
    @Override
    public float getPositionY() {
        return this.position.y;
    }

    public void setPositionY(float yCoordinate) {
        if (yCoordinate > 200) {
            this.position.y = 190;
        } else if (yCoordinate < 0) {
            this.position.y = 25;
        } else {
            this.position.y = yCoordinate;
        }
    }
    
    public void setPositionX(float xCoordinate) {
        if (xCoordinate > 200) {
            this.position.x = 190;
        } else if (xCoordinate < 0) {
            this.position.x = 70;
        } else {
            this.position.x = xCoordinate;
        }
    }
    public String chosenDifficulty(double difficulty) {
        if (difficulty == 0.5) {
            return "Easy";
        } else if (difficulty == 0.75) {
            return "Medium";
        } else if (difficulty == 1.0) {
            return "Hard";
        }
        return null;
    }
}
