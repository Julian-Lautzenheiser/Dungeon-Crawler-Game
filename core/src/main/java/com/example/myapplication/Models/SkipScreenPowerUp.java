package com.example.myapplication.Models;

public class SkipScreenPowerUp extends PlayerDecorator {
    private boolean isVis;

    public SkipScreenPowerUp(Player decoratedPlayer) {
        super(decoratedPlayer);
        isVis = true;
    }

    public void play() {
        super.play();
        this.decoratedPlayer.newScreen(0);
        this.decoratedPlayer.newScreen(1);
        this.decoratedPlayer.setHealth(this.decoratedPlayer.getHealth() - 20);
    }

    public boolean isVisible() {
        return isVis;
    }
}
