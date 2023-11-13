package com.example.myapplication.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
public class SkeletonEnemy implements Enemy {
    // Implement attributes and behaviors for the Skeleton
    // e.g., sprite, speed, size
    private Vector2 velocity;
    private Vector2 position;
    private int damage;
    private int health;

    private boolean direction;
    Player player = Player.getInstance();
    
    public SkeletonEnemy() {
        this.position = new Vector2(0, 0);
        this.velocity = new Vector2(3, 5);
        this.damage = 0;
        this.health = 60;
    }
    
    @Override
    public void move() {
        if(getPositionY() > 190) {
            direction = false;
        }
        if(direction){
            setPositionY(this.position.y + this.velocity.y);
            return;
        } else {
            setPositionY(this.position.y - this.velocity.y);
            if(getPositionY() < 80) {
                direction = true;
            }
        }
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
        return "Skeleton";
    }
}
