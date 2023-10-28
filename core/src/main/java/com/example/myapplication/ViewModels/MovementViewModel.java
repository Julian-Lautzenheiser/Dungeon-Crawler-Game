package com.example.myapplication.ViewModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.example.myapplication.Models.Player;
import com.example.myapplication.Models.PlayerMovement;
import com.example.myapplication.Models.Subscriber;

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
    public void updatePosition(String level) { //Move in the corresponding direction up to a collision object
        Vector2 velocity = new Vector2(0,0);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            velocity.x = -player.getMaxVelocity();
            checkCollision(velocity, level);
            playerMovement.left();

        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            velocity.x = player.getMaxVelocity();
            checkCollision(velocity, level);
            playerMovement.right();

        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) { //Move player down
            velocity.y = -player.getMaxVelocity();
            checkCollision(velocity, level);
            playerMovement.down();

        } else if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.UP)) { //Move player up
            velocity.y = player.getMaxVelocity();
            checkCollision(velocity, level);
            playerMovement.up();
        }
    }
    public void checkCollision(Vector2 velocity, String level) {
        Vector2 position = player.getPosition();

        Rectangle spriteRect = rectPool.obtain();
        spriteRect.set(position.x, position.y, player.getWidth(), player.getHeight());

        //Perform collision detection and response on each axis separately
        //If the player is moving right, check the tiles to the right of their
        //edge box, otherwise check the ones to the left
        int startX, endX, startY, endY;
        if (velocity.x > 0) {
            startX = endX = (int)(position.x + player.getWidth() + velocity.x);
        } else {
            startX = endX = (int)(position.x + velocity.x);
        }
        startY = (int)(position.y);
        endY = (int)(position.y + player.getHeight());

        getTiles(startX, startY, endX, endY, tiles, level);
        spriteRect.x += velocity.x;
        for (Rectangle tile : tiles) {
            if (spriteRect.overlaps(tile)) {
                velocity.x = 0;
                break;
            }
        }
        spriteRect.x = position.x;

        //If player is moving upwards, check the tiles above its edge box
        //Otherwise check the ones below
        if (velocity.y > 0) {
            startY = endY = (int)(position.y + player.getHeight() + velocity.y);
        } else {
            startY = endY = (int)(position.y + velocity.y);
        }
        startX = (int)(position.x);
        endX = (int)(position.x + player.getWidth());
        getTiles(startX, startY, endX, endY, tiles, level);
        spriteRect.y += velocity.y;
        for (Rectangle tile : tiles) {
            if (spriteRect.overlaps(tile)) {
                velocity.y = 0;
                break;
            }
        }
        rectPool.free(spriteRect);

        player.getVelocity().set(velocity);

        /*Vector2 velocity = new Vector2(player.getMaxVelocity(), player.getMaxVelocity());
        TiledMap map = new TmxMapLoader().load(level);
        TiledMapTileLayer collisionLayer = (TiledMapTileLayer)map.getLayers().get("Walls and Objects");
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

        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int)xScaled, (int)yScaled);
        if (cell != null && cell.getTile() != null) {
            return true;
        }
        return false;   
    }

    public void getTiles(int startX, int startY, int endX, int endY, Array<Rectangle> tiles, String level) {
        TiledMap map = new TmxMapLoader().load(level);
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        rectPool.freeAll(tiles);
        tiles.clear();
        for (int y = startY; y <= endY; y++) {
            for (int x = startX; x <= endX; x++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);
                if (cell != null) {
                    Rectangle rect = rectPool.obtain();
                    rect.set(x, y, 1, 1);
                    tiles.add(rect);
                }
            }
        }
    }
}
