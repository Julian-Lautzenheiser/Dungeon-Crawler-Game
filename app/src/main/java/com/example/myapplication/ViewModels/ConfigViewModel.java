package com.example.myapplication.ViewModels;


import androidx.lifecycle.ViewModel;

import com.example.myapplication.Model.MainRepository;
import com.example.myapplication.Model.Player;


public class ConfigViewModel extends ViewModel {
    private Player player = Player.getInstance();
    private MainRepository mainRepo = new MainRepository();
    public ConfigViewModel() {
    }

    public void setPlayer(int radioId, String name, int spriteId) {
        double difficulty = mainRepo.calcDifficulty(radioId);
        player.setDifficulty(difficulty);
        player.setHealth(mainRepo.calcHealth(difficulty));
        player.setName(name);
        player.setSprite(spriteId);
    }
}
