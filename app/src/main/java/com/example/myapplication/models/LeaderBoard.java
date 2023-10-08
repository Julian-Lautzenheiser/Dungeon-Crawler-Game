package com.example.myapplication.models;

public class LeaderBoard {
    private static LeaderBoard leaderboard;
    
    private LeaderBoard() {
        this.leaderboard = null;
    }
    
    public static LeaderBoard getLeaderboard() {
        if (leaderboard == null) {
            leaderboard = new LeaderBoard();
        }
        return leaderboard;
    }
}
