package com.example.myapplication.models;

public class GameScore {

    private int playerScore;
    private static GameScore gameScore;
    
    /*
     * @param playerScore - current player score
     */
    private GameScore() {
        this.playerScore = 0;
    }
    
    public static GameScore getGameScore() {
        if (gameScore == null) {
            gameScore = new GameScore();
        }
        return gameScore;
    }
    
    private int getPlayerScore() {
        return playerScore;
    }
    
    public int decreaseScore() {
        int currScore = getPlayerScore();
        return currScore;
    }
}
