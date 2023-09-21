package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConfigurationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuration_screen);
        Button startBtn = findViewById(R.id.StartGameButton);
        TextView nameBox = findViewById(R.id.UsernameText);

        //Check if name is valid, if so then set difficulty based on radioButton
        startBtn.setOnClickListener(v -> {
            String name = (String) nameBox.getText();
            if (name == null || name.trim() == "") {
                finish(); //Leave current listener if name is not valid
            } else {
                RadioGroup difficultyRadioGroup = findViewById(R.id.difficultyRadioGroup);
                double difficulty = 1;
                int radioId = difficultyRadioGroup.getCheckedRadioButtonId();
                if (radioId == R.id.radioEasy) {
                    difficulty = 0.5;
                } else if (radioId == R.id.radioMedium) {
                    difficulty = 0.75;
                } else if (radioId == R.id.radioHard) {
                    difficulty = 1;
                } else {
                    difficulty = 0.5;
                }

                RadioGroup spriteRadioGroup = findViewById(R.id.spriteRadioGroup);
                int sprite = 1;
                int spriteId = spriteRadioGroup.getCheckedRadioButtonId();

                if (spriteId == R.id.radioOne) { //Puts the sprite as an extra
                    sprite = 1;
                } else if (spriteId == R.id.radioTwo) {
                    sprite = 2;
                } else if (spriteId == R.id.radioThree) {
                    sprite = 3;
                } else {
                    sprite = 1;
                }

                Intent game = new Intent(ConfigurationActivity.this, GameActivity.class);
                game.putExtra("difficulty", difficulty);
                game.putExtra("sprite", sprite);
                startActivity(game);
                finish();
            }
        });
    }
}
