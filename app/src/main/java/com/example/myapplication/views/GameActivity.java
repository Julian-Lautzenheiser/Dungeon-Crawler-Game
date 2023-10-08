package com.example.myapplication.views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.R;

public class GameActivity extends AppCompatActivity {


    private ImageView playerView;
    private TextView nameText;
    private TextView healthText;
    
    private TextView difficultyText;

    private float playerX;
    private float playerY;
    private double difficulty;
    private ConstraintLayout gameLayout;
    private int screenWidth;
    private int screenHeight;
    private final int playerTextOffset = 100;
    private final int healthBase = 100;
    private final int maxSize = 300;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameLayout = findViewById(R.id.gameLayout);
        Button exitButton = findViewById(R.id.buttonExit);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        // Spawn player in middle of screen
        playerX = screenWidth / 2;
        playerY = screenHeight / 2;

        int sprite = getIntent().getIntExtra("sprite", 1);
        playerView = new ImageView(this);
        if (sprite == 1) { //Sets the sprite based on input
            playerView.setImageResource(R.drawable.sword_warrior); //Warrior - Sword
        } else if (sprite == 2) {
            playerView.setImageResource(R.drawable.wizard_warrior); //Mage - Wizard
        } else if (sprite == 3) {
            playerView.setImageResource(R.drawable.fighter_warrior); //Fighter - Fighter
        }
        playerView.setAdjustViewBounds(true); //Allows resizing of sprite while keeping aspect ratio
        playerX /= 2; //TEMP
        playerY -= 300; //MOVES PLAYER CHARACTER INTO WHITESPACE
        playerView.setX(playerX);
        playerView.setY(playerY);
        playerView.setMaxHeight(maxSize); //Makes the sprite not crazy big
        playerView.setMaxWidth(maxSize);

        gameLayout.addView(playerView);

        nameText = new TextView(this);
        nameText.setText("Name: " + getIntent().getStringExtra("name"));
        nameText.setX(playerX);
        nameText.setY(playerY - playerTextOffset);
        nameText.setTextColor(Color.parseColor("#FFFFFF"));
        gameLayout.addView(nameText); //Creates name and attaches it to character

        difficulty = getIntent().getDoubleExtra("difficulty", 0.5);
        healthText = new TextView(this);
        //Subtracting health by difficulty increases health with lower difficulty
        healthText.setText("HP: " + Double.toString((healthBase - difficulty)));
        healthText.setTextColor(Color.parseColor("#FFFFFF"));
        healthText.setX(playerX);
        healthText.setY(playerY + playerView.getMaxHeight() + playerTextOffset);
        gameLayout.addView(healthText); //Creates health text and attaches it to character
        
        difficultyText = new TextView(this);
        if (difficulty == 0) {
            difficultyText.setText("Difficulty: Easy");
        } else if (difficulty == 25) {
            difficultyText.setText("Difficulty: Medium");
        } else if (difficulty == 50) {
            difficultyText.setText("Difficulty: Hard");
        }
        difficultyText.setX(playerX);
        difficultyText.setY(playerY + playerView.getMaxHeight());
        difficultyText.setTextColor(Color.parseColor("#FFFFFF"));
        gameLayout.addView(difficultyText);
        
        exitButton.setOnClickListener(v -> {
            Intent end = new Intent(GameActivity.this, GameEndActivity.class);
            startActivity(end);
            finish();
        });
    }
}
