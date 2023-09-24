package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ConfigurationActivity extends AppCompatActivity {

    private ImageView spritePhoto;
    /** @noinspection checkstyle:Indentation, checkstyle:Indentation, checkstyle:Indentation */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuration_screen);
        Button startBtn = findViewById(R.id.StartGameButton);
        EditText nameBox = findViewById(R.id.UsernameText);
        spritePhoto = new ImageView(this);
        //Check if name is valid, if so then set difficulty based on radioButton
        startBtn.setOnClickListener(v -> {
            String name = nameBox.getText().toString();
            if (name.trim().isEmpty()) {
                startBtn.setClickable(false);
            } else {
                startBtn.setClickable(true);
            }
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
            } else if (spriteId == R.id.Sprite2) {
                sprite = 2;
            } else if (spriteId == R.id.Sprite3) {
                sprite = 3;
            } else {
                sprite = 1;
            }
            switch (sprite) {
                case 1:
                    spritePhoto.setImageResource(R.drawable.sword_warrior);
                    break;
                case 2:
                    spritePhoto.setImageResource(R.drawable.wizard_warrior);
                    break;
                case 3:
                    spritePhoto.setImageResource(R.drawable.fighter_warrior);
                    break;
                default:
                    spritePhoto.setImageResource(R.drawable.sword_warrior);
                    break;
            }
            Intent game = new Intent(ConfigurationActivity.this, GameActivity.class);
            game.putExtra("difficulty", difficulty);
            game.putExtra("sprite", sprite);
            startActivity(game);
            finish();
        });
    }
}