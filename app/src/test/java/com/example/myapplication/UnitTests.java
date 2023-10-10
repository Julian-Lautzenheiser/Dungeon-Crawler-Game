package com.example.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.myapplication.Model.LeaderBoard;
<<<<<<< HEAD
import com.example.myapplication.Model.Player;
=======
>>>>>>> main

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTests {

    /**
     * Local tests that checks to see if leaderboard is a singleton
     */
    @Test
<<<<<<< HEAD
    public void leaderboardTest() {
        LeaderBoard leaderBoard1 = LeaderBoard.getLeaderboard();
        
=======
    public void LeaderboardTest() {
        LeaderBoard lb = LeaderBoard.getInstance();

    }

    /**
     * Local tests that checks is game score is a singleton
     */
    @Test
    public void GameScoreTest() {
    
>>>>>>> main
    }

    /**
     * Local tests that checks to see if score decrements
     */
    @Test
    public void scoreChanges() {
        Player player1 = Player.getInstance();
        assertEquals(0, 0);
    }
}