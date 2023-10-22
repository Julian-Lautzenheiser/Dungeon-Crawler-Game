package com.example.myapplication.Model;

public class GameScore {

    private int playerScore;
    private static GameScore gameScore;
    
    /*
     * @param playerScore - current player score
     */
    private GameScore() {
        this.playerScore = 0;
    }
    
    public static GameScore getGameScoreInstance() {
        if (gameScore == null) {
            gameScore = new GameScore();
        }
        return gameScore;
    }
    
    private int getPlayerScore() {
        return playerScore;
    }
    
    public int decreaseScore() {
        int currScore = getPlayerScore() - 1;

        if (currScore < 0) {
            currScore = 0;
        }
        return currScore;
    }
}
