package com.example.myapplication.ViewModels;

import androidx.lifecycle.ViewModel;

import com.example.myapplication.Model.LeaderBoard;
import com.example.myapplication.Model.LeaderboardScore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LeaderboardViewModel extends ViewModel {
    private LeaderBoard leaderboard = LeaderBoard.getInstance();
    public LeaderboardViewModel() {}

    public void addScore(LeaderboardScore s) {
        ArrayList<LeaderboardScore> table = leaderboard.getTable();
        table.add(s);
        table.sort(Comparator.comparingDouble(a -> a.score));

        if (table.size() > 5) {
            table.remove(5);
        }
        Collections.reverse(table);
    }

    public ArrayList<LeaderboardScore> getTable () {
        return leaderboard.getTable();
    }
}
