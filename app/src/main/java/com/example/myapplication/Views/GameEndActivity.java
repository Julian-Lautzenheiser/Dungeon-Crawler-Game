package com.example.myapplication.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.Model.LeaderBoard;
import com.example.myapplication.Model.LeaderboardScore;
import com.example.myapplication.R;
import com.example.myapplication.ViewModels.GameViewModel;
import com.example.myapplication.ViewModels.LeaderboardViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GameEndActivity extends AppCompatActivity {
    private LeaderboardViewModel leaderboardViewModel;
    private GameViewModel gameViewModel;
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
        Button restartBtn = findViewById(R.id.playagainButton);
        leaderboardViewModel = new ViewModelProvider(this).get(LeaderboardViewModel.class);
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        ListView leaderboardList = findViewById(R.id.list);
        TextView recent = findViewById(R.id.recent);


        LeaderboardScore displayScore = new LeaderboardScore(gameViewModel.getPlayerName(), gameViewModel.getPlayerScore());

        recent.setText(displayScore.name + " (" + displayScore.score + ") - " + displayScore.datetime);

        leaderboardViewModel.addScore(displayScore);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, generateLeaderText());

        leaderboardList.setAdapter(adapter);

        restartBtn.setOnClickListener(v -> {
            Intent config = new Intent(GameEndActivity.this, MainActivity.class);
            startActivity(config);
            finish();
        });
    }

    public ArrayList<String> generateLeaderText() {
        ArrayList<String> finalText = new ArrayList<String>();
        for (LeaderboardScore ls : leaderboardViewModel.getTable()) {
            finalText.add(ls.name + " (" + ls.score + ") - " + ls.datetime);
        }

        return finalText;
    }
}
