package com.example.myapplication.ViewModels;

import android.widget.RadioGroup;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.Model.MainRepository;
import com.example.myapplication.R;
import com.example.myapplication.Model.Player;

import java.nio.charset.MalformedInputException;

public class ConfigViewModel extends ViewModel {
    Player player = Player.getInstance();
    MainRepository mainRepo = new MainRepository();
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
