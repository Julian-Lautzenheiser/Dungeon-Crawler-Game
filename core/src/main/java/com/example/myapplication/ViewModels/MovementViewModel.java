package com.example.myapplication.ViewModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
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
    private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
        @Override
        protected Rectangle newObject() {
            return new Rectangle();
        }
    };
    private Array<Rectangle> tiles = new Array<Rectangle>();
    public List<Enemy> enemyList = new ArrayList<Enemy>();
    public void addSubscriber(Enemy E) {
        enemyList.add(E);
    }

    public void removeSubscriber(Enemy E) {
        enemyList.remove(E);
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
    }
    public void checkCollision(Vector2 velocity, String level) {
        Vector2 position = player.getPosition();
        Rectangle spriteRect = rectPool.obtain();
        spriteRect.set(position.x, position.y, player.getWidth(), player.getHeight());

        TiledMap map = new TmxMapLoader().load(level);
        MapLayer layer = map.getLayers().get("Walls");
        MapObjects objects = layer.getObjects();

        //Perform collision detection and response on each axis separately
        //If the player is moving right, check the tiles to the right of their
        //edge box, otherwise check the ones to the left
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

        /*spriteRect.y += velocity.y;
        for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {
            Rectangle rectangle = rectangleObject.getRectangle();
            if (Intersector.overlaps(rectangle, spriteRect)) {
                velocity.y = 0;
            }
        }*/

        player.getVelocity().set(velocity);


        /*Vector2 velocity = new Vector2(player.getMaxVelocity(), player.getMaxVelocity());
        TiledMap map = new TmxMapLoader().load(level);
        TiledMapTileLayer collisionLayer =
            (TiledMapTileLayer)map.getLayers().get("Walls and Objects");
        int tileSize = collisionLayer.getTileWidth();

        float xScaled = playerX / tileSize;
        float yScaled = playerY / tileSize;

        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int)xScaled, (int)yScaled);
        if (cell != null && cell.getTile() != null) {
            return velocity;
        }
        velocity.x = 0;
        velocity.y = 0;
        return velocity;*/
    }
    
    public boolean checkExit(float x, float y, String level) {
        TiledMap map = new TmxMapLoader().load(level);
        TiledMapTileLayer collisionLayer = (TiledMapTileLayer) map.getLayers().get("Doors");
        int tileSize = collisionLayer.getTileWidth();
        float xScaled = x / tileSize;
        float yScaled = (y) / tileSize;

        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) xScaled, (int) yScaled);
        if (cell != null && cell.getTile() != null) {
            return true;
        }
        return false;   
    }

    public void checkPlayerObjectCollision(Vector2 velocity) {
        Vector2 position = player.getPosition();
        Rectangle spriteRect = rectPool.obtain();


        //Perform collision detection and response on each axis separately
        //If the player is moving right, check the tiles to the right of their
        //edge box, otherwise check the ones to the left
        Vector2 initPos = new Vector2(position);

        position.x += velocity.x;
        position.y += velocity.y;

        //CHECK FOR ENEMY COLLISION
        Rectangle enemyRect = rectPool.obtain();
        for (Enemy E : enemyList) {
                if(position.x < (E.getPositionX() + 5) && (position.x + player.getWidth()/2) > E.getPositionX() &&
                        position.y <  (E.getPositionY() + 10) && (position.y + player.getHeight()/2) > E.getPositionY()) {
                            playerEnemyCollide(E);
                            velocity.x = -velocity.x;
                            velocity.y = -velocity.y;
            }
        }
        position.set(initPos);
        player.getVelocity().set(velocity);
    }


    public void playerEnemyCollide(Enemy E) {
        player.setHealth(player.getHealth() - E.attack());
    }
}
