package com.example.myapplication.Model;

import java.util.ArrayList;

public class LeaderBoard {
    private static LeaderBoard leaderboard = null;
    private static ArrayList<LeaderboardScore> table;

    private LeaderBoard() {
        this.table = new ArrayList<>();
    }
    
    public static LeaderBoard getInstance() {
        if (leaderboard == null) {
            synchronized (LeaderBoard.class) {
                if (leaderboard == null) {
                    leaderboard = new LeaderBoard();
                }
            }
        }
        return leaderboard;
    }

    public ArrayList<LeaderboardScore> getTable() {
        return this.table;
    }

    public int getSize() {
        return this.table.size();
    }
}
