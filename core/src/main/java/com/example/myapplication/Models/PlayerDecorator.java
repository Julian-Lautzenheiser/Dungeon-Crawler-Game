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

    void resetPlayer() {
        decoratedPlayer.resetPlayer();
    }

    String getSprite() {
        return decoratedPlayer.getSprite();
    }

    void setSprite(String sprite) {
        decoratedPlayer.setSprite(sprite);
    }

    void setHealth(int health) {
        decoratedPlayer.setHealth(health);
    }

    int getHealth() {
        return decoratedPlayer.getHealth();
    }

    String getName() {
        return decoratedPlayer.getName();
    }

    void setDifficulty(double difficulty) {
        decoratedPlayer.setDifficulty(difficulty);
    }

    double getDifficulty() {
        return decoratedPlayer.getDifficulty();
    }

    void setName(String name) {
        decoratedPlayer.setName(name);
    }

    float getPlayerX() {
        return decoratedPlayer.getPlayerX();
    }

    float getPlayerY() {
        return decoratedPlayer.getPlayerY();
    }

    Vector2 getPosition() {
        return decoratedPlayer.getPosition();
    }

    Vector2 getVelocity() {
        return decoratedPlayer.getVelocity();
    }

    void setPlayerX(float playerX) {
        decoratedPlayer.setPlayerX(playerX);
    }

    void setPlayerY(float playerY) {

    }

    double getScore() {
        return decoratedPlayer.getScore();
    }

    void setScore(double newScore) {
        decoratedPlayer.setScore(newScore);
    }

    float getHeight() {
        return decoratedPlayer.getHeight();
    }

    float getWidth() {
        return decoratedPlayer.getWidth();
    }

    void setHeight(float height) {
        decoratedPlayer.setHeight(height);
    }

    void setWidth(float width) {
        decoratedPlayer.setWidth(width);
    }

    int getLevel() {
        return decoratedPlayer.getLevel();
    }

    void setLevel(int level) {
        decoratedPlayer.setLevel(level);
    }

    void newScreen(int level) {
        decoratedPlayer.newScreen(level);
    }

    float getMaxVelocity() {
        return decoratedPlayer.getMaxVelocity();
    }

    void damageTaken(int damage) {
        decoratedPlayer.damageTaken(damage);
    }

    int getDamage() {
        return decoratedPlayer.getDamage();
    }

}
