package com.example.myapplication.Models;

import com.badlogic.gdx.math.Vector2;

abstract class PlayerDecorator {
    protected Player decoratedPlayer;

    void play() {
        return;
    }

    public PlayerDecorator(Player decoratedPlayer) {
        this.decoratedPlayer = decoratedPlayer;
    }
}
