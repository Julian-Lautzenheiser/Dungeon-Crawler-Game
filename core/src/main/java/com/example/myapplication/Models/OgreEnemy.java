package com.example.myapplication.Models;

import com.badlogic.gdx.math.Vector2;
public class OgreEnemy implements Enemy {
    // Implement attributes and behaviors for the Ogre
    // e.g., sprite, speed, size
    private Vector2 velocity;
    private Vector2 position;
    private int damage;
    private int health;
    private Player player = Player.getInstance();
    
    public OgreEnemy() {
        this.velocity = new Vector2(18, 20);
        this.position = new Vector2(0, 0);
        this.damage = 15;
        this.health = 130;
    }
    @Override
    public void move() {
        // Implement movement logic
        if (getPositionY() >= 195) {
            setPositionY(this.position.y - this.velocity.y);
        } else if (getPositionY() < 90) {
            setPositionY(this.position.y - this.velocity.y);
        }
    }

    @Override
    public int attack() {
        switch (chosenDifficulty(player.getDifficulty())) {
        case "Easy":
            this.damage = 9;
            break;
        case "Medium":
            this.damage = 15;
            break;
        case "Hard":
            this.damage = 21;
            break;
        default:
            this.damage = 9;
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
        if (xCoordinate > 340) {
            this.position.x = 340;
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

    @Override
    public String toString() {
        return "Ogre";
    }
}
