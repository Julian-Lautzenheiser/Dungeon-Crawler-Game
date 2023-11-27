package com.example.myapplication.Models;

public class SkipScreenPowerUp extends PlayerDecorator {
    public SkipScreenPowerUp(Player decoratedPlayer) {
        super(decoratedPlayer);
    }

    public void play() {
        super.play();
        this.decoratedPlayer.newScreen(0);
        this.decoratedPlayer.newScreen(1);
    }
}
