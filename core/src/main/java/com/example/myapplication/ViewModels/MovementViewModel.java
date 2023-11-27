package com.example.myapplication.ViewModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.example.myapplication.Models.Enemy;
import com.example.myapplication.Models.Player;
import com.example.myapplication.Models.PlayerMovement;
import com.example.myapplication.Models.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class MovementViewModel implements Subscriber {
    private Player player = Player.getInstance();
    private PlayerMovement playerMovement = new PlayerMovement();
    public MovementViewModel() { };
    private Vector2 prevVelocity = new Vector2();
    private List<Enemy> enemyList = new ArrayList<Enemy>();
    private double exitScore = 150 * player.getDifficulty();
    public void addSubscriber(Enemy e) {
        getEnemyList().add(e);
    }

    public void removeSubscriber(Enemy e) {
        getEnemyList().remove(e);
    }

    // Move in the corresponding direction up to a collision object
    public void updatePosition(String level) {
        Vector2 velocity = new Vector2(0, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)
                || Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            velocity.x = -player.getMaxVelocity();
            checkCollision(velocity, level);
            checkPlayerObjectCollision(velocity);
            playerMovement.left();
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)
                || Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            velocity.x = player.getMaxVelocity();
            checkCollision(velocity, level);
            checkPlayerObjectCollision(velocity);
            playerMovement.right();
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)
                || Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) { //Move player down
            velocity.y = -player.getMaxVelocity();
            checkCollision(velocity, level);
            checkPlayerObjectCollision(velocity);
            playerMovement.down();
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)
                || Gdx.input.isKeyJustPressed(Input.Keys.UP)) { //Move player up
            velocity.y = player.getMaxVelocity();
            checkCollision(velocity, level);
            checkPlayerObjectCollision(velocity);
            playerMovement.up();
        }
        prevVelocity.set(velocity);
    }
    public void checkCollision(Vector2 velocity, String level) {
        Vector2 position = player.getPosition();

        TiledMap map = new TmxMapLoader().load(level);
        MapLayer layer = map.getLayers().get("Walls");
        MapObjects objects = layer.getObjects();

        //Perform collision detection
        Vector2 initPos = new Vector2(position);
        position.x += velocity.x;
        position.y += velocity.y;
        for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {
            Rectangle rectangle = rectangleObject.getRectangle();
            if (rectangle.contains(position)) {
                velocity.x = 0;
                velocity.y = 0;
            }
        }
        position.set(initPos);

        player.getVelocity().set(velocity);
    }
    
    public boolean checkExit(String level) {
        Vector2 position = player.getPosition();
        position.add(prevVelocity);

        TiledMap map = new TmxMapLoader().load(level);
        MapLayer layer = map.getLayers().get("ExitDoor");
        MapObjects objects = layer.getObjects();

        //Check if player position is in the door frame
        for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {
            Rectangle rectangle = rectangleObject.getRectangle();
            if (rectangle.contains(position)) {
                double newScore = player.getScore() + exitScore;
                player.setScore(newScore);
                return true;
            }
        }
        return false;
    }

    public void checkPlayerObjectCollision(Vector2 velocity) {
        Vector2 position = player.getPosition();
        Rectangle spriteRect = new Rectangle(player.getPlayerX(),
                player.getPlayerY(), player.getWidth(), player.getHeight());
        
        //Perform collision detection and response on each axis separately
        //If the player is moving right, check the tiles to the right of their
        //edge box, otherwise check the ones to the left
        Vector2 initPos = new Vector2(position);

        position.x += velocity.x;
        position.y += velocity.y;

        //CHECK FOR ENEMY COLLISION
<<<<<<< HEAD
        Rectangle enemyRect = rectPool.obtain();
        for (Enemy enemy : enemyList) {
            Rectangle enemyRectangle = new Rectangle(enemy.getPositionX(),
                    enemy.getPositionY(), enemy.getWidth(), enemy.getHeight());
            if (enemyRectangle.contains(position)) {
                playerEnemyCollide(enemy);
                velocity.x = -velocity.x;
                velocity.y = -velocity.y;
=======
        for (Enemy E : enemyList) {
            Rectangle enemyRectangle = new Rectangle(E.getPositionX(), E.getPositionY(), E.getWidth(), E.getHeight());
                if(enemyRectangle.contains(position) && E.getAlive()) {
                            playerEnemyCollide(E);
                            velocity.x = -velocity.x;
                            velocity.y = -velocity.y;
>>>>>>> 2e1b5ec375da03d4c72efae318a72189e6c270b4
            }
        }
        position.set(initPos);
        player.getVelocity().set(velocity);
    }
    
    public void playerEnemyCollide(Enemy enemy) {
        player.setHealth(player.getHealth() - enemy.attack());
    }

    public List<Enemy> getEnemyList() {
        return enemyList;
    }

    public void setEnemyList(List<Enemy> enemyList) {
        this.enemyList = enemyList;
    }
    
    public double getExitScore() {
        return this.exitScore;
    }
}
