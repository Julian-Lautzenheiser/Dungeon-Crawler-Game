package com.example.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.myapplication.Model.LeaderBoard;
import com.example.myapplication.Model.LeaderboardScore;
import com.example.myapplication.Model.Player;
import java.util.ArrayList;
import com.example.myapplication.ViewModels.GameViewModel;
import com.example.myapplication.ViewModels.LeaderboardViewModel;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTests {

    /**
     * Local test that checks to see if Plauer is a singleton
     */
    @Test
    public void playerTest() {
        Player player1 = Player.getInstance();
        player1.setDifficulty(0.5);
        player1.setHealth(10);
        player1.setSprite(2);
        player1.setSprite(3);
    }
    
    /**
     * Local tests that checks to see if leaderboard is a singleton
     */
    @Test
    public void leaderboardTest() {
        ArrayList<LeaderboardScore> leaderboardTest = new ArrayList<>();
        leaderboardTest.add(3, new LeaderboardScore("Andrew", 105));
        leaderboardTest.add(0, new LeaderboardScore("Nawal", 200));
        leaderboardTest.add(1, new LeaderboardScore("Jai", 140));
        leaderboardTest.add(2, new LeaderboardScore("Julian", 120));
    
        LeaderboardViewModel lbView = new LeaderboardViewModel();
        
        LeaderBoard leaderBoard1 = LeaderBoard.getInstance();
        lbView.addScore(new LeaderboardScore("Andrew", 105));
        LeaderBoard leaderBoard2 = LeaderBoard.getInstance();
        lbView.addScore(new LeaderboardScore("Nawal", 200));
        LeaderBoard leaderBoard3 = LeaderBoard.getInstance();
        lbView.addScore(new LeaderboardScore("Jai", 140));
        LeaderBoard leaderBoard4 = LeaderBoard.getInstance();
        lbView.addScore(new LeaderboardScore("Julian", 120));
        
        LeaderBoard leaderboard = LeaderBoard.getInstance();
        ArrayList<LeaderboardScore> leaderboardScore1 = leaderboard.getTable();
        
        assertEquals(leaderboardTest.size(), leaderboardScore1.size());
        assertArrayEquals(leaderboardTest.toArray(), leaderboardScore1.toArray());
    }
    
    /**
     * Local tests that checks to see if score decrements
     */
    @Test
    public void scoreChanges() {
        double targetScore = 0.0;
        Player player1 = Player.getInstance();
        GameViewModel gModel = new GameViewModel();
        double newScore = gModel.getPlayerScore();
        while (player1.getScore() != 0) {
            gModel.decreaseScore();
            newScore = player1.getScore();
        }
        assertEquals(targetScore, newScore, 0.5);
    }

    /**
     * Local test that checks to see if leaderboard size is 5 if there are more than 5 entries
     */
    @Test
    public void leaderBoardSizeExceedsCheck() {
        ArrayList<LeaderboardScore> leaderboardTest = new ArrayList<>();
        leaderboardTest.add(new LeaderboardScore("Andrew", 0));
        leaderboardTest.add(new LeaderboardScore("Nawal", 0));
        leaderboardTest.add(new LeaderboardScore("Jai", 0));
        leaderboardTest.add(new LeaderboardScore("Julian", 0));
        leaderboardTest.add(new LeaderboardScore("Sukrutha", 0));
    
        LeaderBoard leaderBoard1 = LeaderBoard.getInstance();
        LeaderBoard leaderBoard2 = LeaderBoard.getInstance();
        LeaderBoard leaderBoard3 = LeaderBoard.getInstance();
        LeaderBoard leaderBoard4 = LeaderBoard.getInstance();
        LeaderBoard leaderBoard5 = LeaderBoard.getInstance();
        LeaderBoard leaderBoard6 = LeaderBoard.getInstance();
    
        LeaderBoard leaderboard = LeaderBoard.getInstance();
        ArrayList<LeaderboardScore> leaderboardScore1 = leaderboard.getTable();
    
        assertEquals(leaderboardTest.size(), leaderboardScore1.size());
        assertArrayEquals(leaderboardTest.toArray(), leaderboardScore1.toArray());
    }
}