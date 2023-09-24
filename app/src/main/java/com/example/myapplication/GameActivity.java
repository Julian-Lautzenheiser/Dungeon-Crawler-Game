package com.example.myapplication;


import android.content.Intent;
<<<<<<< HEAD
import android.os.Bundle;
=======
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
>>>>>>> 3cc2779fda2edfc7f634b429fabdc7aedcae45e8
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class GameActivity extends AppCompatActivity {


    private ImageView playerView;
    private TextView nameText;
    private TextView healthText;

<<<<<<< HEAD
    private float playerX;
    private float playerY;
    private double difficulty;
    private ConstraintLayout gameLayout;
    private int screenWidth;
    private int screenHeight;
    private final int PLAYER_TEXT_OFFSET = 100;
    private final int HEALTH_BASE = 100;
    private final int MAX_SIZE = 300;
=======
    private float playerX, playerY;
    private double difficulty;
    ConstraintLayout gameLayout;
    int screenWidth;
    int screenHeight;
    final int PLAYER_TEXT_OFFSET = 100;
    final int HEALTH_BASE = 100;
    final int MAX_SIZE = 300;
>>>>>>> 3cc2779fda2edfc7f634b429fabdc7aedcae45e8



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
<<<<<<< HEAD
        playerView.setAdjustViewBounds(true); //Allows resizing of sprite while keeping aspect ratio
=======
        playerView.setAdjustViewBounds(true); //Allows for resizing of sprite while keeping aspect ratio
>>>>>>> 3cc2779fda2edfc7f634b429fabdc7aedcae45e8
        playerX /= 2; //TEMP
        playerY -= 300; //MOVES PLAYER CHARACTER INTO WHITESPACE
        playerView.setX(playerX);
        playerView.setY(playerY);
        playerView.setMaxHeight(MAX_SIZE); //Makes the sprite not crazy big
        playerView.setMaxWidth(MAX_SIZE);

        gameLayout.addView(playerView);

        nameText = new TextView(this);
        nameText.setText(getIntent().getStringExtra("name"));
        nameText.setX(playerX);
        nameText.setY(playerY - PLAYER_TEXT_OFFSET);
        gameLayout.addView(nameText); //Creates name and attaches it to character

        difficulty = getIntent().getDoubleExtra("difficulty", 0.5);
        healthText = new TextView(this);
<<<<<<< HEAD
        //Dividing health by decimal increases health with lower difficulty
        healthText.setText(Double.toString((HEALTH_BASE / difficulty)));

=======
        healthText.setText(Double.toString((HEALTH_BASE / difficulty))); //Dividing health by decimal increases health with lower difficulty
>>>>>>> 3cc2779fda2edfc7f634b429fabdc7aedcae45e8
        healthText.setX(playerX);
        healthText.setY(playerY + playerView.getMaxHeight());
        gameLayout.addView(healthText); //Creates health text and attaches it to character



        exitButton.setOnClickListener(v -> {
            Intent end = new Intent(GameActivity.this, GameEndActivity.class);
            startActivity(end);
            finish();
        });

    }
}
