package com.example.myapplication.Models;

public class HealthPowerUp extends PlayerDecorator {

    public HealthPowerUp(Player decoratedPlayer) {
        super(decoratedPlayer);
    }

    public void play() {
        super.play();
        this.decoratedPlayer.setHealth(this.decoratedPlayer.getHealth() + 50);
    }
}
