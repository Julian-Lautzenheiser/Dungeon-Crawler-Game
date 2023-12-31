
package com.example.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.myapplication.Models.DemonEnemy;
import com.example.myapplication.Models.Enemy;
import com.example.myapplication.Models.GoblinEnemy;
import com.example.myapplication.Models.HealthPowerUp;
import com.example.myapplication.Models.LeaderBoard;
import com.example.myapplication.Models.LeaderboardScore;
import com.example.myapplication.Models.OgreEnemy;
import com.example.myapplication.Models.Player;
import com.example.myapplication.Models.ScorePowerUp;
import com.example.myapplication.Models.SkeletonEnemy;
import com.example.myapplication.Models.SkipScreenPowerUp;
import com.example.myapplication.ViewModels.AttackingViewModel;
import com.example.myapplication.ViewModels.Dungeon;

import java.rmi.server.Skeleton;
import java.util.ArrayList;
import java.util.List;

import com.example.myapplication.ViewModels.EnemyFactory;
import com.example.myapplication.ViewModels.LeaderboardViewModel;
import com.example.myapplication.ViewModels.MovementViewModel;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTests {
    // Sprint 5 Tests
    //////////////////////////////////////
    //////////////////////////////////////

    @Test
    public void checkHealthPowerupDecorator() {
        Player player = Player.getInstance();
        int initHealth = player.getHealth();
        HealthPowerUp hpu = new HealthPowerUp(player);
        hpu.play();
        int finalHealth = player.getHealth();
        assert(finalHealth == initHealth + 50);
    }

    @Test
    public void checkScorePowerupDecorator() {
        Player player = Player.getInstance();
        double initScore = player.getScore();
        ScorePowerUp spu = new ScorePowerUp(player);
        spu.play();
        double finalScore = player.getScore();
        assert(finalScore == initScore + 10.0);
    }

    @Test
    public void checkSkipScreenPowerupDecorator() {
        Player player = Player.getInstance();
        double initLevel = player.getLevel();
        SkipScreenPowerUp sspu = new SkipScreenPowerUp(player);
        sspu.play();
        double finalLevel = player.getLevel();
        assert(finalLevel == initLevel + 2);
    }

    @Test
    public void checkGoblinEasyScore() {
        EnemyFactory factory = new EnemyFactory();
        Enemy goblin = factory.createEnemy("Goblin");
        
        Player player = Player.getInstance();
        player.setDifficulty(0.5);
        
        double finalScore = goblin.getScore() * player.getDifficulty();
        
        goblin.setScore(player.getDifficulty());
        
        double expectedScore = goblin.getScore();
        
        assertEquals(finalScore, expectedScore, 0.5);
    }
    @Test
    public void checkOgreMediumScore() {
        EnemyFactory factory = new EnemyFactory();
        Enemy ogre = factory.createEnemy("Ogre");
        
        Player player = Player.getInstance();
        player.setDifficulty(0.75);
        
        double finalScore = ogre.getScore() * player.getDifficulty();
        
        ogre.setScore(player.getDifficulty());
        
        double expectedScore = ogre.getScore();
        
        assertEquals(finalScore, expectedScore, 0.5);
    }

    @Test
    public void checkDemonHardScore() {
        EnemyFactory factory = new EnemyFactory();
        Enemy demon = factory.createEnemy("Demon");
        
        Player player = Player.getInstance();
        player.setDifficulty(1);
        
        double finalScore = demon.getScore() * player.getDifficulty();
        
        demon.setScore(player.getDifficulty());
        
        double expectedScore = demon.getScore();
        
        assertEquals(finalScore, expectedScore, 0.5);
    }

    @Test
    public void checkGoblinDies() {
        List<Enemy> enemyList = new ArrayList<Enemy>();
        Enemy goblin = new GoblinEnemy();
        goblin.setPositionX(20);
        goblin.setPositionY(20);
        goblin.setWidth(40);
        goblin.setHeight(50);
        enemyList.add(goblin);

        Player player = Player.getInstance();
        player.setPlayerX(10);
        player.setPlayerY(20);

        AttackingViewModel attackingViewModel = new AttackingViewModel();
        attackingViewModel.attack(enemyList);

        assertFalse(goblin.getAlive());
    }

    @Test
    public void checkOgreDeathPoints() {
        List<Enemy> enemyList = new ArrayList<Enemy>();
        Enemy ogre = new OgreEnemy();
        ogre.setPositionX(20);
        ogre.setPositionY(20);
        ogre.setWidth(40);
        ogre.setHeight(50);
        enemyList.add(ogre);

        Player player = Player.getInstance();
        player.setPlayerX(10);
        player.setPlayerY(20);

        player.setDifficulty(1.0);
        double expectedScore = player.getScore() + ogre.getScore();

        AttackingViewModel attackingViewModel = new AttackingViewModel();
        attackingViewModel.attack(enemyList);

        assertEquals(expectedScore, player.getScore(), 0.5);
    }

    @Test
    public void checkDemonDeathPoints() {
        List<Enemy> enemyList = new ArrayList<Enemy>();
        Enemy demon = new DemonEnemy();
        demon.setPositionX(20);
        demon.setPositionY(20);
        demon.setWidth(40);
        demon.setHeight(50);
        enemyList.add(demon);

        Player player = Player.getInstance();
        player.setPlayerX(10);
        player.setPlayerY(20);

        player.setDifficulty(1.0);
        double expectedScore = player.getScore() + demon.getScore();

        AttackingViewModel attackingViewModel = new AttackingViewModel();
        attackingViewModel.attack(enemyList);

        assertEquals(expectedScore, player.getScore(), 0.5);
    }

    @Test
    public void checkGoblinDeathPoints() {
        List<Enemy> enemyList = new ArrayList<Enemy>();
        Enemy goblin = new GoblinEnemy();
        goblin.setPositionX(20);
        goblin.setPositionY(20);
        goblin.setWidth(40);
        goblin.setHeight(50);
        enemyList.add(goblin);

        Player player = Player.getInstance();
        player.setPlayerX(10);
        player.setPlayerY(20);

        player.setDifficulty(1.0);
        double expectedScore = player.getScore() + goblin.getScore();

        AttackingViewModel attackingViewModel = new AttackingViewModel();
        attackingViewModel.attack(enemyList);

        assertEquals(expectedScore, player.getScore(), 0.5);
    }

    @Test
    public void checkSkeletonDeathPoints() {
        List<Enemy> enemyList = new ArrayList<Enemy>();
        Enemy skeleton = new SkeletonEnemy();
        skeleton.setPositionX(20);
        skeleton.setPositionY(20);
        skeleton.setWidth(40);
        skeleton.setHeight(50);
        enemyList.add(skeleton);

        Player player = Player.getInstance();
        player.setPlayerX(10);
        player.setPlayerY(20);

        player.setDifficulty(1.0);
        double expectedScore = player.getScore() + skeleton.getScore();

        AttackingViewModel attackingViewModel = new AttackingViewModel();
        attackingViewModel.attack(enemyList);

        assertEquals(expectedScore, player.getScore(), 0.5);
    }
    
    // Sprint 4 Tests
    //////////////////////////////////////
    //////////////////////////////////////

    @Test
    public void checkEnemyFactoryOgre() {
        EnemyFactory factory = new EnemyFactory();
        Enemy ogre = factory.createEnemy("OGRE");
        assertTrue(ogre.toString() == "Ogre");
    }
    
    @Test
    public void checkEnemyFactoryGoblin() {
        EnemyFactory factory = new EnemyFactory();
        Enemy goblin = factory.createEnemy("GOBLIN");
        assertTrue(goblin.toString() == "Goblin");
    }
    
    @Test
    public void checkEnemyFactoryDemon() {
        EnemyFactory factory = new EnemyFactory();
        Enemy demon = factory.createEnemy("demon");
        assertTrue(demon.toString() == "Demon");
    }
    
    @Test
    public void checkEnemyFactorySkeleton() {
        EnemyFactory factory = new EnemyFactory();
        Enemy skeleton = factory.createEnemy("SkELEToN");
        assertTrue(skeleton.toString() == "Skeleton");
    }

    @Test
    public void checkSubscribers(){
        EnemyFactory factory = new EnemyFactory();
        Enemy skeleton = factory.createEnemy("Skeleton");

        MovementViewModel movement = new MovementViewModel();
        movement.addSubscriber(skeleton);

        movement.getEnemyList().get(0).equals(skeleton);

    }
    @Test
    public void checkRemoveSubscribers(){
        EnemyFactory factory = new EnemyFactory();
        Enemy skeleton = factory.createEnemy("Skeleton");

        MovementViewModel movement = new MovementViewModel();
        movement.addSubscriber(skeleton);
        movement.getEnemyList().get(0).equals(skeleton);

        movement.removeSubscriber(skeleton);

        assert(movement.getEnemyList().size() == 0);
    }

    @Test
    public void setGoblinPos(){
        EnemyFactory factory = new EnemyFactory();
        Enemy goblin = factory.createEnemy("Goblin");
        goblin.setPositionX(150);
        goblin.setPositionY(150);
        assert(goblin.getPositionY() == 150);
        assert(goblin.getPositionX() == 150);

    }

    @Test
    public void setOgrePos(){
        EnemyFactory factory = new EnemyFactory();
        Enemy ogre = factory.createEnemy("Ogre");

        ogre.setPositionX(150);
        ogre.setPositionY(150);
        assert(ogre.getPositionY() == (float) 150);
        assert(ogre.getPositionX() == (float) 150);
    }
    @Test
    public void setSkelePos(){
        EnemyFactory factory = new EnemyFactory();
        Enemy skeleton = factory.createEnemy("Skeleton");

        skeleton.setPositionX(150);
        skeleton.setPositionY(150);
        assert(skeleton.getPositionX() == (float) 150);
        assert(skeleton.getPositionY() ==  (float) 150);
    }

    @Test
    public void setEnemyDiff(){
        EnemyFactory factory = new EnemyFactory();
        Enemy skeleton = factory.createEnemy("Skeleton");
        assert(skeleton.chosenDifficulty(0.5) == "Easy");

    }
    
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
}

    //Sprint 3 Tests
    
    /**
     * Local test to make sure you can set player pos

    @Test
    public void playerSetPosTest() {
        Player player1 = Player.getInstance();
        player1.setPlayerX(100);
        player1.setPlayerY(100);
        
        
        assert (player1.getPlayerX() == 100);
        assert (player1.getPlayerY() == 100);
    }



}



    /**
     * Local test to make sure player cant have negative position

    @Test
    public void playerBoundsTest() {
        Player player1 = Player.getInstance();
        player1.setPlayerX(-100);
        player1.setPlayerY(-100);
        assert (player1.getPlayerX() == 300);
        assert (player1.getPlayerY() == 100);
    }

     Local test to test wall collision
    //    @Test
    //    public void WallCollisionTest() {
    //        Player player1 = Player.getInstance();
    //        player1.setPlayerX(300);
    //        player1.setPlayerY(100);
    //        PlayerMovement playerMovement = new PlayerMovement();
    //        MovementViewModel movement = new MovementViewModel();
    //
    //        //TiledMap map = new TmxMapLoader().load("room1.tmx");
    //        for(int i = 0; i < 500; i++){
    //            if (!movement.checkCollision(player1.getPlayerX() - 10, player1.getPlayerY(), "room1.tmx")) {
    //                player1.setPlayerX(player1.getPlayerX() - 10);
    //            }
    //        }
    //        assert(player1.getPlayerX() > 0);
    //    }
     */
  
    /*
    @Test
    public void WallCollisionTest() {
        Player player1 = Player.getInstance();
        player1.setPlayerX(300);
        player1.setPlayerY(100);
        PlayerMovement playerMovement = new PlayerMovement();
        MovementViewModel movement = new MovementViewModel();

        //TiledMap map = new TmxMapLoader().load("room1.tmx");

        for(int i = 0; i < 500; i++){
            int velocity = movement.checkCollision(player1.getPlayerX() - 10, player1.getPlayerY(), "room1.tmx");
            player1.setPlayerX(player1.getPlayerX() - velocity);
        }
        assert(player1.getPlayerX() > 0);
    }
    */

     /* Local test to make sure score can't be set to a negative value
     */
    /*
    @Test
    public void moveUpLeft() {
        Player player1 = Player.getInstance();
        PlayerMovement playerMovement = new PlayerMovement();
        
        int expectedX = player1.getPlayerX() - 20;
        int expectedY = player1.getPlayerY() + 10;
        
        playerMovement.left(player1.getMaxVelocity());
        playerMovement.left(player1.getMaxVelocity());
        playerMovement.up(player1.getMaxVelocity());
        
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
            playerMovement.down(player1.getMaxVelocity());
            if (i != 2) {
                playerMovement.right(player1.getMaxVelocity());
            }
        }
        
        int newX = player1.getPlayerX();
        int newY = player1.getPlayerY();
        
        assertEquals(expectedX, newX);
        assertEquals(expectedY, newY);
    }
    */

    /*
    @Test
    public void checkInitialLevel() {
        Player player = Player.getInstance();
        assertEquals(player.getLevel(), 0);
    }

    @Test
    public void checkLevelOneIncrease() {
        Player player = Player.getInstance();
        player.newScreen(0);
        assertEquals(player.getLevel(), 1);
    }

    @Test
    public void checkLevelTwoIncrease() {
        Player player = Player.getInstance();
        player.newScreen(1);
        assertEquals(player.getLevel(), 2);
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

    
    @Test
    public void checkSetHeight() {
        Player player = Player.getInstance();
        float expectedHeight = 5;
        player.setHeight(0);
        
        assertTrue(expectedHeight == player.getHeight());
    }

    @Test
    public void checkSetWidth() {
        Player player = Player.getInstance();
        float expectedWidth = 5;
        player.setWidth(0);
        
        assertTrue(expectedWidth == player.getWidth());
    }

    */


