package com.example.myapplication.ViewModels;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.Model.GameModel;
import com.example.myapplication.Model.Player;

public class GameViewModel extends ViewModel {

    private Player player = Player.getInstance();
    private GameModel gModel = new GameModel();

    public GameViewModel() {
    }

    public int getSpriteImage() {
        return player.getSpritePicId();
    }
    public void setScreenDimensions(int width, int height) {
        gModel.setScreenDimensions(width, height);
    }
    public void setPlayerPos(float x, float y) {
        player.setPos(x, y);
    }
    public float getPlayerX() {
        return player.getX();
    }
    public float getPlayerY() {
        return player.getY();
    }
    public int getMaxSize() {
        return gModel.getMaxSize();
    }
    public int getHealthBase() {
        return gModel.getHealthBase();
    }
    public int getPlayerTextOffset() {
        return gModel.getPlayerTextOffset();
    }

    public String getDifficulty() {
        double difficulty = player.getDifficulty();
        if (difficulty == 0.5) {
            return "Easy";
        } else if (difficulty == 0.75) {
            return "Medium";
        } else if (difficulty == 1.0) {
            return "Hard";
        } else {
            return "Easy";
        }
    }
    public int getPlayerHealth() {
        return player.getHealth();
    }
    public String getPlayerName() {
        return player.getName();
    }

    public float getPlayerScore() {
        return player.getScore();
    }
}
