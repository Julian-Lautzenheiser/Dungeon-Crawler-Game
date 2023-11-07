package com.example.myapplication.Models;

public class GoblinEnemy implements Enemy {
    // Implement attributes and behaviors for the Goblin
    // e.g., sprite, speed, size
    int damage;
    int velocity;
    
    @Override
    public void move() {
        // Implement movement logic
        this.velocity = 7;
    }

    @Override
    public void attack() {
        // Implement attack logic
        this.damage = 10;
    }

    @Override
    public void display() {
        // Implement display logic
        
    }
}
