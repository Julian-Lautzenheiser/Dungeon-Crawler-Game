package com.example.myapplication.Models;

public class ScorePowerUp extends PlayerDecorator {
    public ScorePowerUp(Player decoratedPlayer) {
        super(decoratedPlayer);
    }

    public void play() {
        super.play();
        this.decoratedPlayer.setScore(this.decoratedPlayer.getScore() + 10.0);
    }
}
