package com.example.myapplication.ViewModels;


import com.example.myapplication.Models.LeaderBoard;
import com.example.myapplication.Models.LeaderboardScore;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LeaderboardViewModel{
    private LeaderBoard leaderboard = LeaderBoard.getInstance();
    public LeaderboardViewModel() { }

    public void addScore(LeaderboardScore s) {
        ArrayList<LeaderboardScore> table = leaderboard.getTable();
        table.add(s);
        table.sort(Comparator.comparingDouble(a -> a.getScore()));

        if (table.size() > 5) {
            table.remove(5);
        }
        Collections.reverse(table);
    }

    public ArrayList<LeaderboardScore> getTable() {
        return leaderboard.getTable();
    }
}
