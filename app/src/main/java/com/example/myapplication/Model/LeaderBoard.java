package com.example.myapplication.Model;

import java.util.ArrayList;
import java.util.Comparator;

public class LeaderBoard {
    private static LeaderBoard leaderboard;
    private static ArrayList<LeaderboardScore> table;
    private static int size;

    private LeaderBoard() {
        this.table = new ArrayList<>();
        this.size = 0;
    }
    
    public static LeaderBoard getInstance() {
        if (leaderboard == null) {
            leaderboard = new LeaderBoard();
        }
        return leaderboard;
    }

    public ArrayList<LeaderboardScore> getTable() {
        return this.table;
    }
}
