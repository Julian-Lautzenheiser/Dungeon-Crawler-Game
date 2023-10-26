package com.example.myapplication.ViewModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.example.myapplication.Models.Player;
import com.example.myapplication.Models.PlayerMovement;
import com.example.myapplication.Models.Subscriber;

public class MovementViewModel implements Subscriber {
    private Player player = Player.getInstance();
    private PlayerMovement playerMovement = new PlayerMovement();
    private final int velocity = 10;
    public MovementViewModel() { };
    private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
        @Override
        protected Rectangle newObject() {
            return new Rectangle();
        }
    };
    private Array<Rectangle> tiles = new Array<Rectangle>();
    public void updatePosition(String level) {
        int velocity;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)
                || Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            velocity = checkCollision(player.getPlayerX() - player.getMaxVelocity(), player.getPlayerY(), level);
            playerMovement.left(velocity);

        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            velocity = checkCollision(player.getPlayerX() + player.getMaxVelocity(), player.getPlayerY(), level);
            playerMovement.right(velocity);

        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)
                || Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) { //Move player down
            velocity = checkCollision(player.getPlayerX(), player.getPlayerY() - player.getMaxVelocity(), level);
            playerMovement.down(velocity);

        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)
                || Gdx.input.isKeyJustPressed(Input.Keys.UP)) { //Move player up
            velocity = checkCollision(player.getPlayerX(), player.getPlayerY() + (player.getMaxVelocity()), level);
            playerMovement.up(velocity);
        }
    }
    public int checkCollision(int playerX, int playerY, String level) {
        //        int startX, endX, startY, endY;
        //        Rectangle spriteRect = rectPool.obtain();
        //        startX = playerX;
        //        endX = (int) (playerX + 64/32f);
        //        startY = playerY;
        //        endY = (int) (playerY + 64/32f);
        //        spriteRect.set(playerX, playerY, 64/32f, 64/32f);
        //        getTiles(startX, startY, endX, endY, tiles);
        //
        //        for (Rectangle tile : tiles) {
        //            if (spriteRect.overlaps(tile)) {
        //                return true;
        //            }
        //        }
        //        return false;

        TiledMap map = new TmxMapLoader().load(level);
        TiledMapTileLayer collisionLayer = (TiledMapTileLayer)
                map.getLayers().get("Walls and Objects");
        int tileSize = collisionLayer.getTileWidth();

        int xScaled = playerX / 32;
        int yScaled = playerY / 32;

        TiledMapTileLayer.Cell cell = collisionLayer.getCell(xScaled, yScaled);
        if (cell != null && cell.getTile() != null) {
            return velocity;
        }
        return 0;
    }
    
    public boolean checkExit(int x, int y, String level) {
        TiledMap map = new TmxMapLoader().load(level);
        TiledMapTileLayer collisionLayer = (TiledMapTileLayer) map.getLayers().get("Doors");
        int tileSize = collisionLayer.getTileWidth();
        int xScaled = x / tileSize;
        int yScaled = (y + 10) / tileSize;

        TiledMapTileLayer.Cell cell = collisionLayer.getCell(xScaled, yScaled);
        if (cell != null && cell.getTile() != null) {
            return true;
        }
        return false;   
    }

    public void getTiles(int startX, int startY, int endX, int endY, Array<Rectangle> tiles) {
        TiledMap map = new TmxMapLoader().load("room1.tmx");
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("walls");
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
