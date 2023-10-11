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

import java.util.Timer;
import java.util.TimerTask;

public class GameScreen2Activity extends AppCompatActivity {


    private ImageView playerView;
    private TextView nameText;
    private TextView healthText;
    
    private TextView difficultyText;
    private ConstraintLayout gameLayout;
    
    private GameViewModel gameViewModel;
    private TextView playerScore;
    private Timer scoreTimer;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room2);
        gameLayout = findViewById(R.id.gameLayout);
        Button nextButton = findViewById(R.id.nextExit2);
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        
        gameViewModel.setScreenDimensions(screenWidth, screenHeight);
        gameViewModel.setPlayerPos(screenWidth / 2, screenHeight / 2); // Spawn player in middle
        
        playerView = new ImageView(this);
        int id = gameViewModel.getSpriteImage();
        playerView.setImageResource(id);
        playerView.setAdjustViewBounds(true); //Allows resizing of sprite while keeping aspect ratio
        float newX = gameViewModel.getPlayerX() / 2;
        float newY = gameViewModel.getPlayerY() - 300;
        
        gameViewModel.setPlayerPos(newX, newY);
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
        newY = gameViewModel.getPlayerY();
        newY += playerView.getMaxHeight() + gameViewModel.getPlayerTextOffset();
        healthText.setY(newY);
        gameLayout.addView(healthText); //Creates health text and attaches it to character
        
        difficultyText = new TextView(this);
        difficultyText.setText("Difficulty: " + gameViewModel.getDifficulty());
        difficultyText.setX(gameViewModel.getPlayerX());
        difficultyText.setY(gameViewModel.getPlayerY() + playerView.getMaxHeight());
        difficultyText.setTextColor(Color.parseColor("#FFFFFF"));
        gameLayout.addView(difficultyText);
        
        playerScore = findViewById(R.id.score);
        playerScore.setText("Score: " + String.valueOf((int) gameViewModel.getPlayerScore()));
        scoreTimer = new Timer();
        scoreTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> gameViewModel.decrementScore(gameViewModel, playerScore));
            }
        }, 1, 2000);
        
        
        nextButton.setOnClickListener(v -> {
            scoreTimer.cancel();
            Intent screen3 = new Intent(GameScreen2Activity.this, GameScreen3Activity.class);
            startActivity(screen3);
            finish();
        });
    }
}