package com.example.myapplication;

import android.content.Intent;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class GameActivity extends AppCompatActivity {


    private PlayerView playerView;
    private float playerX, playerY;
    ConstraintLayout gameLayout;

    private double difficulty;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameLayout = findViewById(R.id.gameLayout);

        Button exitButton = findViewById(R.id.buttonExit);

        exitButton.setOnClickListener(v -> {
            //Intent end = new Intent(GameActivity.this, finaliActivity.class);
            //startActivity(end);
            finish();
        });

    }
}
