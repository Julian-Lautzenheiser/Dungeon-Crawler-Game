package com.example.myapplication.Models;

import com.example.myapplication.Models.PlayerDecorator;

public class HealthPowerUp extends PlayerDecorator {

    public HealthPowerUp(Player decoratedPlayer) {
        super(decoratedPlayer);
    }

    public void play() {
        super.play();
        this.decoratedPlayer.setHealth(this.decoratedPlayer.getHealth() + 50);
    }
}
