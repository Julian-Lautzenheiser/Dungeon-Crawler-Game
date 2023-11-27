package com.example.myapplication.Models;

public class ScorePowerUp extends PlayerDecorator {
    private boolean isVis;

    public ScorePowerUp(Player decoratedPlayer) {
        super(decoratedPlayer);
        isVis = true;
    }

    public void play() {
        super.play();
        this.decoratedPlayer.setScore(this.decoratedPlayer.getScore() + 10.0);
        isVis = false;
    }

    public boolean isVisible() {
        return isVis;
    }
}
