package com.example.myapplication.Models;

import com.badlogic.gdx.math.Vector2;

public class PlayerMovement implements Movement {
    private Player player = Player.getInstance();

    public void left() {
        player.getPosition().add(player.getVelocity());
        player.getVelocity().set(0,0);
    }

    public void right() {
        player.getPosition().add(player.getVelocity());
        player.getVelocity().set(0,0);
    }

    public void down() {
        player.getPosition().add(player.getVelocity());
        player.getVelocity().set(0,0);
    }
    public void up() {
        player.getPosition().add(player.getVelocity());
        player.getVelocity().set(0,0);
    }

}
