package com.example.myapplication.Model;

import android.widget.RadioGroup;

import com.example.myapplication.R;

public class MainRepository {


    private final int base_health = 100;

    public MainRepository() {
    }

    public double calcDifficulty(int radioId) {
        double difficulty = 0;
        if (radioId == R.id.easyDifficulty) {
            difficulty = 0.5;
        } else if (radioId == R.id.mediumDifficulty) {
            difficulty = 0.75;
        } else if (radioId == R.id.hardDifficulty) {
            difficulty = 1.0;
        }
        return difficulty;
    }
    public int calcHealth(double difficulty) {
        return (int)(base_health/difficulty);
    }
}
