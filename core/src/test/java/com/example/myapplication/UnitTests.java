package com.example.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.example.myapplication.Models.LeaderBoard;
import com.example.myapplication.Models.LeaderboardScore;
import com.example.myapplication.Models.Movement;
import com.example.myapplication.Models.Player;
import com.example.myapplication.Models.PlayerMovement;
import com.example.myapplication.ViewModels.Dungeon;
import com.example.myapplication.Models.PlayerMovement;

import java.util.ArrayList;

import com.example.myapplication.ViewModels.LeaderboardViewModel;
import com.example.myapplication.ViewModels.MovementViewModel;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.example.myapplication.Models.Player;
import com.example.myapplication.ViewModels.Dungeon;
import com.example.myapplication.ViewModels.MovementViewModel;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTests {
    //Sprint 2 Tests
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
     * Local tests that checks to see if player score decrements
     */
    @Test
    public void scoreChanges() {
        double targetScore = 0.0;
        Player player1 = Player.getInstance();
        Dungeon gModel = new Dungeon();
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
        LeaderboardViewModel LBVM = new LeaderboardViewModel();
        ArrayList<LeaderboardScore> table = LBVM.getTable();
        assertFalse(LBVM == null);
        
        LBVM.addScore(new LeaderboardScore("Andrew", 0));
        LBVM.addScore(new LeaderboardScore("Nawal", 10));
        LBVM.addScore(new LeaderboardScore("Jai", 20));
        LBVM.addScore(new LeaderboardScore("Julian", 30));
        assertEquals(table.size(), 4);
        LeaderboardScore[] arr = new LeaderboardScore[table.size()];
        
        
        LBVM.addScore(new LeaderboardScore("Sukrutha", 40));
        LBVM.addScore(new LeaderboardScore("Pedro", 50));
        
        assertEquals(table.size(), 5);
    }
    
    /**
     * Local test to check if leaderboard updates
     */
        /*
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
        */
    /**
     * Local test to check player name for whitespace
     */
       /*
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
        */
    
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
    
    //Sprint 3 Tests
  
    /**
     * Local test to make sure you can set player pos
     */
    @Test
    public void playerSetPosTest() {
        Player player1 = Player.getInstance();
        player1.setPlayerX(100);
        player1.setPlayerY(100);


        assert(player1.getPlayerX() == 100);
        assert(player1.getPlayerY() == 100);
    }

    /**
     * Local test to make sure player cant have negative position
     */
    @Test
    public void playerBoundsTest() {
        Player player1 = Player.getInstance();
        player1.setPlayerX(-100);
        player1.setPlayerY(-100);
        assert(player1.getPlayerX() == 300);
        assert(player1.getPlayerY() == 100);
    }



    /**
     * Local test to test wall collision
     */

    @Test
    public void WallCollisionTest() {
        Player player1 = Player.getInstance();
        player1.setPlayerX(300);
        player1.setPlayerY(100);
        PlayerMovement playerMovement = new PlayerMovement();
        MovementViewModel movement = new MovementViewModel();

        //TiledMap map = new TmxMapLoader().load("room1.tmx");

        for(int i = 0; i < 500; i++){
            if (!movement.checkCollision(player1.getPlayerX() - 10, player1.getPlayerY(), "room1.tmx")) {
                player1.setPlayerX(player1.getPlayerX() - 10);
            }
        }
        assert(player1.getPlayerX() > 0);
    }

     * Local test to make sure score can't be set to a negative value
     */
    @Test
    public void moveUpLeft() {
        Player player1 = Player.getInstance();
        PlayerMovement playerMovement = new PlayerMovement();
        
        int expectedX = player1.getPlayerX() - 20;
        int expectedY = player1.getPlayerY() + 10;
        
        playerMovement.left();
        playerMovement.left();
        playerMovement.up();
        
        int newX = player1.getPlayerX();
        int newY = player1.getPlayerY();
        
        assertEquals(expectedX, newX);
        assertEquals(expectedY, newY);
    }

    @Test
    public void moveDownRight() {
        Player player1 = Player.getInstance();
        PlayerMovement playerMovement = new PlayerMovement();
        
        int expectedX = player1.getPlayerX() + 20;
        int expectedY = player1.getPlayerY() - 30;
        
        for (int i = 0; i < 3; i++) {
            playerMovement.down();
            if (i != 2) {
                playerMovement.right();
            }
        }
        
        int newX = player1.getPlayerX();
        int newY = player1.getPlayerY();
        
        assertEquals(expectedX, newX);
        assertEquals(expectedY, newY);
    }

    @Test
    public void checkLevelThreeIncrease() {
        Player player = Player.getInstance();
        player.newScreen(2);
        assertEquals(player.getLevel(), 3);
    }

    @Test
    public void checkLevelFourIncreaseToZero() {
        Player player = Player.getInstance();
        player.newScreen(3);
        assertEquals(player.getLevel(), 0);
    }
}