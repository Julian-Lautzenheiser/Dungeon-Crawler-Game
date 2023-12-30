package com.example.myapplication.Models;

abstract class PlayerDecorator {
    protected Player decoratedPlayer;

    void play() {
        return;
    }

    public PlayerDecorator(Player decoratedPlayer) {
        this.decoratedPlayer = decoratedPlayer;
    }
}
