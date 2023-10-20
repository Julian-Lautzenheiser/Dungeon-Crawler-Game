package com.example.myapplication.ViewModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.example.myapplication.Models.Player;

public class MovementViewModel {
    private Player player = Player.getInstance();
    private final int velocity = 10;

    public MovementViewModel() { }
    public void updatePosition() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            player.setX(player.getX() - velocity);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            player.setX(player.getX() + velocity);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            player.setY(player.getY() - velocity);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            player.setY(player.getY() + velocity);
        }
    }
}
