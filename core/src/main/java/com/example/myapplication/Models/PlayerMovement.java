package com.example.myapplication.Models;

public class PlayerMovement implements Movement {
    private Player player = Player.getInstance();

    public void left(int velocity) {
        player.setPlayerX(player.getPlayerX() - velocity);
    }

    public void right(int velocity) {
        player.setPlayerX(player.getPlayerX() + velocity);
    }

    public void down(int velocity) {
        player.setPlayerY(player.getPlayerY() - velocity);
    }
    public void up(int velocity) {
        player.setPlayerY(player.getPlayerY() + velocity);
    }

}
