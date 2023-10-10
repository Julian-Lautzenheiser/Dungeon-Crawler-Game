package com.example.myapplication.Views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.ViewModels.GameViewModel;

public class GameScreen3Activity extends AppCompatActivity {


    private ImageView playerView;
    private TextView nameText;
    private TextView healthText;

    private TextView difficultyText;
    private ConstraintLayout gameLayout;

    private GameViewModel gameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room3);
        gameLayout = findViewById(R.id.gameLayout);
        Button nextButton = findViewById(R.id.nextExit3);
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);

        gameViewModel.setScreenDimensions(screenWidth, screenHeight);
        gameViewModel.setPlayerPos(screenWidth/2, screenHeight/2); // Spawn player in middle of screen



        playerView = new ImageView(this);
        int id = gameViewModel.getSpriteImage();
        playerView.setImageResource(id);
        playerView.setAdjustViewBounds(true); //Allows resizing of sprite while keeping aspect ratio
        gameViewModel.setPlayerPos(gameViewModel.getPlayerX() / 2, gameViewModel.getPlayerY() - 300);
        playerView.setX(gameViewModel.getPlayerX());
        playerView.setY(gameViewModel.getPlayerY());
        playerView.setMaxHeight(gameViewModel.getMaxSize()); //Makes the sprite not crazy big
        playerView.setMaxWidth(gameViewModel.getMaxSize());
        gameLayout.addView(playerView);

        nameText = new TextView(this);
        nameText.setText("Name: " + gameViewModel.getPlayerName());
        nameText.setX(gameViewModel.getPlayerX());
        nameText.setY(gameViewModel.getPlayerY() - gameViewModel.getPlayerTextOffset());
        nameText.setTextColor(Color.parseColor("#FFFFFF"));
        gameLayout.addView(nameText); //Creates name and attaches it to character

        healthText = new TextView(this);
        //Subtracting health by difficulty increases health with lower difficulty
        healthText.setText("HP: " + gameViewModel.getPlayerHealth());
        healthText.setTextColor(Color.parseColor("#FFFFFF"));
        healthText.setX(gameViewModel.getPlayerX());
        healthText.setY(gameViewModel.getPlayerY() + playerView.getMaxHeight() + gameViewModel.getPlayerTextOffset());
        gameLayout.addView(healthText); //Creates health text and attaches it to character

        difficultyText = new TextView(this);
        difficultyText.setText("Difficulty: " + gameViewModel.getDifficulty());
        difficultyText.setX(gameViewModel.getPlayerX());
        difficultyText.setY(gameViewModel.getPlayerY() + playerView.getMaxHeight());
        difficultyText.setTextColor(Color.parseColor("#FFFFFF"));
        gameLayout.addView(difficultyText);

        nextButton.setOnClickListener(v -> {
            Intent end = new Intent(GameScreen3Activity.this, GameEndActivity.class);
            startActivity(end);
            finish();
        });
    }
}
