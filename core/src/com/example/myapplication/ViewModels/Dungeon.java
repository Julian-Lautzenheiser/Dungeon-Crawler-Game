package com.example.myapplication.ViewModels;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.example.myapplication.Models.Player;
import com.example.myapplication.Views.MainMenuScreen;

public class Dungeon extends Game {

    private Player player = Player.getInstance();
    public SpriteBatch batch;
    public BitmapFont font;
    public TextureAtlas buttonAtlas;
    private int baseHealth = 100;

    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new MainMenuScreen(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    public void setPlayerName(String name) {
        player.setName(name);
    }
    public void setSprite(String sprite) {
        player.setSprite(sprite);
    }
    public double getPlayerScore() {
        return player.getScore();
    }
    public String getSprite() {
        return player.getSprite();
    }
    public String getPlayerName() {
        return player.getName();
    }
    public void setDifficulty(double difficulty) {
        player.setDifficulty(difficulty);
        player.setHealth((int)((baseHealth)/difficulty));
    }

    /*public void setPlayer(int radioId, String name, int spriteId) {
        double difficulty = mainRepo.calcDifficulty(radioId);
        player.setDifficulty(difficulty);
        player.setHealth(mainRepo.calcHealth(difficulty));
        player.setName(name);
        player.setSprite(spriteId);
    }*/
}
