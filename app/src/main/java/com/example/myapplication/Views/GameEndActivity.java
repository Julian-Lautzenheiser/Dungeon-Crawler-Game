package com.example.myapplication.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.myapplication.R;

public class GameEndActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);
        
        Button restart = findViewById(R.id.playagainButton);
        
        restart.setOnClickListener(v -> {
            Intent end = new Intent(GameEndActivity.this, MainActivity.class);
            startActivity(end);
            finish();
        });
    }
}
