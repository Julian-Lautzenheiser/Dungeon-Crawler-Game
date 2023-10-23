package com.example.myapplication.ViewModels;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.example.myapplication.Models.Player;
import com.example.myapplication.Views.MainMenuScreen;

public class Dungeon extends Game {
    private Player player = Player.getInstance();
    private SpriteBatch batch;
    private BitmapFont font;
    private TextureAtlas buttonAtlas;
    private int baseHealth = 100;
    
    public void create() {
        setBatch(new SpriteBatch());
        setFont(new BitmapFont());
        this.setScreen(new MainMenuScreen(this));
    }
    
    public void render() {
        super.render();
    }
    
    public void dispose() {
        getBatch().dispose();
        getFont().dispose();
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
        player.setHealth((int) ((baseHealth) / difficulty));
    }
    
    public void decreaseScore() {
        if (player.getScore() > 0) {
            player.setScore(player.getScore() - 5);
        }
    }
    
    public SpriteBatch getBatch() {
        return batch;
    }
    
    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }
    
    public BitmapFont getFont() {
        return font;
    }
    
    public void setFont(BitmapFont font) {
        this.font = font;
    }
    
    public TextureAtlas getButtonAtlas() {
        return buttonAtlas;
    }
    
    public void setButtonAtlas(TextureAtlas buttonAtlas) {
        this.buttonAtlas = buttonAtlas;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }

    public TextureAtlas getButtonAtlas() {
        return buttonAtlas;
    }

    public void setButtonAtlas(TextureAtlas buttonAtlas) {
        this.buttonAtlas = buttonAtlas;
    }

    /*public void setPlayer(int radioId, String name, int spriteId) {
        double difficulty = mainRepo.calcDifficulty(radioId);
        player.setDifficulty(difficulty);
        player.setHealth(mainRepo.calcHealth(difficulty));
        player.setName(name);
        player.setSprite(spriteId);
    }*/
}