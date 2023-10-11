package com.example.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

import android.widget.RadioGroup;

import com.example.myapplication.Model.LeaderBoard;
import com.example.myapplication.Model.LeaderboardScore;
import com.example.myapplication.Model.Player;
import com.example.myapplication.ViewModels.ConfigViewModel;
import com.example.myapplication.ViewModels.GameViewModel;
import java.util.ArrayList;
import com.example.myapplication.R;

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
    public void playerSingletonTest() {
        Player player1 = Player.getInstance();
        assertFalse(player1 == null);

        Player player2 = Player.getInstance();
        assertTrue(player1 == player2);
    }

    /**
     * Local tests that checks to see if leaderboard is a singleton
     */
    @Test
    public void leaderboardSingletonTest() {
        LeaderBoard lb1 = LeaderBoard.getInstance();
        assertFalse(lb1 == null);

        LeaderBoard lb2 = LeaderBoard.getInstance();
        assertTrue(lb1 == lb2);
    }
    
    /**
    * Local test that checks player values update
    */
    @Test
    public void playerUpdates() {
        //Player Health Logic needs work
        Player player1 = Player.getInstance();
        player1.setDifficulty(0.5);
        assertTrue(player1.getDifficulty() == 0.5);
        assertTrue(player1.getHealth() == 5);

        player1.setDifficulty(0.75);
        assertTrue(player1.getDifficulty() == 0.75);
        assertTrue(player1.getHealth() == 15);

        player1.setDifficulty(1);
        assertTrue(player1.getDifficulty() == 1);
        assertTrue(player1.getHealth() == 25);
    }

    /**
     * Local tests that checks to see if player score decrements
     */
    @Test
    public void scoreChanges() {
        double targetScore = 0.0;
        Player player1 = Player.getInstance();
        GameViewModel gModel = new GameViewModel();
        double newScore = player1.getScore();
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
        //LeaderBoard model vs LeaderBoard ViewModel??
        LeaderBoard lb = LeaderBoard.getInstance();
        ArrayList<LeaderboardScore> leaderboardTest = lb.getTable();
        assertFalse(lb == null);

        leaderboardTest.add(new LeaderboardScore("Andrew", 0));
        leaderboardTest.add(new LeaderboardScore("Nawal", 0));
        leaderboardTest.add(new LeaderboardScore("Jai", 0));
        leaderboardTest.add(new LeaderboardScore("Julian", 0));
        
        LeaderBoard leaderBoard1 = LeaderBoard.getInstance();
        //lbView.addScore(new LeaderboardScore("Andrew", 105));
        LeaderBoard leaderBoard2 = LeaderBoard.getInstance();
        //lbView.addScore(new LeaderboardScore("Nawal", 200));
        LeaderBoard leaderBoard3 = LeaderBoard.getInstance();
        //lbView.addScore(new LeaderboardScore("Jai", 140));
        LeaderBoard leaderBoard4 = LeaderBoard.getInstance();
        //lbView.addScore(new LeaderboardScore("Julian", 120));
        
        LeaderBoard leaderboard = LeaderBoard.getInstance();
        ArrayList<LeaderboardScore> leaderboardScore1 = leaderboard.getTable();
        
        assertEquals(leaderboardTest.size(), leaderboardScore1.size());
        assertArrayEquals(leaderboardTest.toArray(), leaderboardScore1.toArray());

        assertTrue(lb.getSize() == 4);

        leaderboardTest.add(new LeaderboardScore("Sukrutha", 0));
        leaderboardTest.add(new LeaderboardScore("Pedro", 0));

        //Size Does NOT Currently Update//
        assertTrue(lb.getSize() == 5);
    }

    /**
     * Local test to check if leaderboard updates
     */
    @Test
    public void leaderboardUpdateTest(){
        LeaderboardViewModel LBVM = new LeaderboardViewModel();
        ArrayList<LeaderboardScore> table = LBVM.getTable();
        assertFalse(LBVM == null);

        LBVM.addScore(new LeaderboardScore("Andrew", 0));
        LBVM.addScore(new LeaderboardScore("Nawal", 10));
        LBVM.addScore(new LeaderboardScore("Jai", 20));
        LBVM.addScore(new LeaderboardScore("Julian", 30));
        assertEquals(table.size(), 4);
        LeaderboardScore[] arr = new LeaderboardScore[table.size()];

        // Convert ArrayList into an array
        table.toArray(arr);


        assertEquals(arr[3].getName(), "Andrew");
        assertTrue(arr[3].getScore() == 0);
        assertEquals(arr[2].getName(), "Nawal");
        assertTrue(arr[2].getScore() == 10);
        assertEquals(arr[1].getName(), "Jai");
        assertTrue(arr[1].getScore() == 20);
        assertEquals(arr[0].getName(), "Julian");
        assertTrue(arr[0].getScore() == 30);
    }

    /**
     * Local test to check player name for whitespace
     */
    @Test
    public void playerNameCheck() {
        Player player1 = Player.getInstance();
        ConfigViewModel configViewModel = new ConfigViewModel();
        configViewModel.setPlayer(1,"Andrew", 2);
        assertTrue(player1.getName() == "Andrew");
    }

    @Test
    public void playerEasyDifficultyCheck() {
        int id = R.id.easyDifficulty;
        Player player1 = Player.getInstance();
        ConfigViewModel configViewModel = new ConfigViewModel();
        configViewModel.setPlayer(id,"Andrew", 2);
        assertTrue(player1.getHealth() == 200);
    }

    @Test
    public void playerMediumDifficultyCheck() {
        int id = R.id.mediumDifficulty;
        Player player = Player.getInstance();
        ConfigViewModel configViewModel = new ConfigViewModel();
        configViewModel.setPlayer(id, "Andrew", 1);
        assertTrue(player.getHealth() == 133);
    }

    @Test
    public void playerHardDifficultyCheck() {
        int id = R.id.hardDifficulty;
        Player player = Player.getInstance();
        ConfigViewModel configViewModel = new ConfigViewModel();
        configViewModel.setPlayer(id, "Andrew", 1);
        assertTrue(player.getHealth() == 100);
    }
    
    /**
     * Local test to make sure score can't be set to a negative value
     */
    @Test
    public void scoreNegTest() {
        Player player1 = Player.getInstance();
        double expectedScore = player1.getScore();
        player1.setScore(-100.0);
        double newScore = player1.getScore();
        assertTrue(expectedScore == newScore);
    }
}