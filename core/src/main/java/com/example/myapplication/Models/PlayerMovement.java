package com.example.myapplication.Models;


public class PlayerMovement implements Movement {
    private Player player = Player.getInstance();

    public void left() {
        player.getPosition().add(player.getVelocity());
    }

    public void right() {
        player.getPosition().add(player.getVelocity());
    }

    public void down() {
        player.getPosition().add(player.getVelocity());
    }
    public void up() {
        player.getPosition().add(player.getVelocity());
    }

}
