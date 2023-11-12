package com.example.myapplication.Models;

import com.badlogic.gdx.math.Vector2;

public class GoblinEnemy implements Enemy {
    // Implement attributes and behaviors for the Goblin
    // e.g., sprite, speed, size
    Player player = Player.getInstance();
    int damage;
    Vector2 velocity;
    Vector2 position;
    int health;
    
    public GoblinEnemy() {
        this.velocity = new Vector2(5, 3);
        this.position = new Vector2(0, 0);
        this.damage = 0;
        this.health = 80;
    }
    
    @Override
    public void move() {
        // Implement movement logic
    }

    @Override
    public int attack() {
        switch(chosenDifficulty(player.getDifficulty())) {
            case "Easy":
                this.damage = 3;
                break;
            case "Medium":
                this.damage = 7;
                break;
            case "Hard":
                this.damage = 11;
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
        /*if (yCoordinate > 200) {
            this.position.y = 190;
        } else if (yCoordinate < 0) {
            this.position.y = 25;
        } else {
            this.position.y = yCoordinate;
        }
        */
    }
    
    public void setPositionX(float xCoordinate) {
        /*
        if (xCoordinate > 200) {
            this.position.x = 190;
        } else if (xCoordinate < 0) {
            this.position.x = 70;
        } else {
            this.position.x = xCoordinate;
        }
        */
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
        return "Goblin";
    }
}
