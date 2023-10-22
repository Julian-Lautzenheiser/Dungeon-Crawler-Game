package com.example.myapplication.Models;

public class PlayerMovement implements Movement {
    private Player player = Player.getInstance();
    private int velocity = 10;

    public void left() {
        player.setPlayerX(player.getPlayerX() - velocity);
    }

    public void right() {
        player.setPlayerX(player.getPlayerX() + velocity);
    }

    public void down() {
        player.setPlayerY(player.getPlayerY() - velocity);
    }
    public void up() {
        player.setPlayerY(player.getPlayerY() + velocity);
    }

}
