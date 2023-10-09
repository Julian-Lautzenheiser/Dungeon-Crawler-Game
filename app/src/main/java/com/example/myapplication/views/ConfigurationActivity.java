package com.example.myapplication.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.ViewModels.ConfigViewModel;

public class ConfigurationActivity extends AppCompatActivity {

    private ConfigViewModel configViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuration_screen);
        Button startBtn = findViewById(R.id.StartGameButton);
        EditText nameBox = findViewById(R.id.UsernameText);
        configViewModel = new ViewModelProvider(this).get(ConfigViewModel.class);

        startBtn.setOnClickListener(v -> {
            String name = nameBox.getText().toString();

            RadioGroup difficultyRadioGroup = findViewById(R.id.difficultyGroup);
            int radioId = difficultyRadioGroup.getCheckedRadioButtonId();

            RadioGroup spriteRadioGroup = findViewById(R.id.CharacterGroup);
            int spriteId = spriteRadioGroup.getCheckedRadioButtonId();

            if (!name.trim().isEmpty()) {
                configViewModel.setPlayer(radioId, name, spriteId);
                Intent game = new Intent(ConfigurationActivity.this, GameActivity.class);

                startActivity(game);
                finish();
            }
        });
    }
}