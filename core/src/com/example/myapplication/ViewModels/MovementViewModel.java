package com.example.myapplication.ViewModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.example.myapplication.Models.Player;
import com.badlogic.gdx.math.Rectangle;

public class MovementViewModel {
    private Player player = Player.getInstance();
    //private static MovementViewModel MVM = null;
    private final int velocity = 10;

    public MovementViewModel(){};

/*    public static MovementViewModel getMovementViewModel() {
        if (MVM == null) {
            synchronized (MovementViewModel.class) {
                if (MVM == null) {
                    MVM = new MovementViewModel();
                }
            }
        }
        return MVM;
    }*/
    public void updatePosition(String level) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            if(!checkCollision(player.getX() - velocity, player.getY(), level)) {
                player.setX(player.getX() - velocity);
            }
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            if(!checkCollision(player.getX() +velocity, player.getY()  , level)) {
                player.setX(player.getX() + velocity);
            }
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if(!checkCollision(player.getX() , player.getY() - velocity , level)) {
                player.setY(player.getY() - velocity);
            }
            return;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if(!checkCollision(player.getX() , player.getY() + (velocity), level)) {
                player.setY(player.getY() + velocity);
            }
            return;
        }
    }
    public boolean checkCollision(int playerX, int playerY, String level) {
        TiledMap map = new TmxMapLoader().load(level);
        TiledMapTileLayer CollisionLayer = (TiledMapTileLayer) map.getLayers().get("Walls and Objects");
        int tileSize = CollisionLayer.getTileWidth();

        int xScaled = playerX / tileSize;
        int yScaled = playerY / tileSize;

        TiledMapTileLayer.Cell cell = CollisionLayer.getCell(xScaled, yScaled);
        if (cell != null && cell.getTile() != null) {
           return true;
        }
        return false;
    }

    public boolean checkExit(int x, int y, String level) {
        TiledMap map = new TmxMapLoader().load(level);
        TiledMapTileLayer CollisionLayer = (TiledMapTileLayer) map.getLayers().get("Doors");
        int tileSize = CollisionLayer.getTileWidth();

        int xScaled = x / tileSize;
        int yScaled = (y + 10) / tileSize;

        TiledMapTileLayer.Cell cell = CollisionLayer.getCell(xScaled, yScaled);
        if (cell != null && cell.getTile() != null) {
            return true;
        }
        return false;

    }
}
