package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
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
        ImageView image = findViewById(R.id.spriteimage);

        //Check if name is valid, if so then set difficulty based on radioButton
        startBtn.setOnClickListener(v -> {
            String name = (String) nameBox.getText();
            if (name == null || name.trim() == "") {
                finish(); //Leave current listener if name is not valid
            } else {
                RadioGroup difficultyRadioGroup = findViewById(R.id.difficultyGroup);
                double difficulty = 1;
                int radioId = difficultyRadioGroup.getCheckedRadioButtonId();
                if (radioId == R.id.easyDifficulty) {
                    difficulty = 0.5;
                } else if (radioId == R.id.mediumDifficulty) {
                    difficulty = 0.75;
                } else if (radioId == R.id.hardDifficulty) {
                    difficulty = 1;
                } else {
                    difficulty = 0.5;
                }

                RadioGroup spriteRadioGroup = findViewById(R.id.CharacterGroup);
                int sprite = 1;
                int spriteId = spriteRadioGroup.getCheckedRadioButtonId();

                if (spriteId == R.id.Sprite1) { //Puts the sprite as an extra
                    sprite = 1;
                    image.setImageDrawable(getResources().getDrawable(R.drawable.sword_warrior));
                } else if (spriteId == R.id.Sprite2) {
                    sprite = 2;
                    image.setImageDrawable(getResources().getDrawable(R.drawable.wizard_warrior));
                } else if (spriteId == R.id.Sprite3) {
                    sprite = 3;
                    image.setImageDrawable(getResources().getDrawable(R.drawable.fighter_warrior));
                } else {
                    sprite = 1;
                    image.setImageDrawable(getResources().getDrawable(R.drawable.sword_warrior));
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
