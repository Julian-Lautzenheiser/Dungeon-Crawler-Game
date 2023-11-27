package com.example.myapplication.Models;

public class HealthPowerUp extends PlayerDecorator {
    private boolean isVis;

    public HealthPowerUp(Player decoratedPlayer) {
        super(decoratedPlayer);
        isVis = true;
    }

    public void play() {
        super.play();
        this.decoratedPlayer.setHealth(this.decoratedPlayer.getHealth() + 50);
        isVis = false;
    }

    public boolean isVisible() {
        return isVis;
    }
}
