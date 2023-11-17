package com.example.myapplication.Models;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
public class OgreEnemy implements Enemy {
    // Implement attributes and behaviors for the Ogre
    // e.g., sprite, speed, size
    private Vector2 velocity;
    private Vector2 position;
    private int damage;
    private int health;
    int width;
    int height;
    Player player = Player.getInstance();
    
    public OgreEnemy() {
        this.velocity = new Vector2(6, 0);
        this.position = new Vector2(0,0);
        this.damage = (int)(10 * player.getDifficulty());
        this.health = 130;
    }
    @Override
    public void move(String level) {
        TiledMap map = new TmxMapLoader().load(level);
        MapLayer layer = map.getLayers().get("Walls");
        MapObjects objects = layer.getObjects();

        //Perform collision detection
        Vector2 newPos = position;
        newPos.x += velocity.x;
        newPos.y += velocity.y;
        for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {
            Rectangle rectangle = rectangleObject.getRectangle();
            if (rectangle.contains(newPos)) {
                velocity.x = velocity.x * -1;
                velocity.y = velocity.y * -1;
            }
        }
        position.add(velocity);
        Rectangle enemyRectangle = new Rectangle(position.x, position.y, getWidth(), getHeight()-5);
        if (enemyRectangle.contains(player.getPosition())) {
            player.damageTaken(damage);
        }
    }

    @Override
    public int attack() {
        switch(chosenDifficulty(player.getDifficulty())) {
            case "Easy":
                this.damage = 9;
                break;
            case "Medium":
                this.damage = 15;
                break;
            case "Hard":
                this.damage = 21;
                break;
        }
        return this.damage;
    }

    @Override
    public void damageTaken() {
        //Implement hp logic
        if (this.health > 0) {
            this.health -= player.getDamage();
        }
    }

    @Override
    public float getPositionX() {
        return this.position.x;
    }
    
    @Override
    public float getPositionY() {
        return this.position.y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setPositionY(float yCoordinate) {
        if (yCoordinate > 200) {
            this.position.y = 190;
        } else if (yCoordinate < 0) {
            this.position.y = 25;
        } else {
            this.position.y = yCoordinate;
        }
    }

    public void setPositionX(float xCoordinate) {
        if (xCoordinate > 340) {
            this.position.x = 340;
        } else if (xCoordinate < 0) {
            this.position.x = 70;
        } else {
            this.position.x = xCoordinate;
        }
    }
    
    public String chosenDifficulty(double difficulty) {
        if (difficulty == 0.5) {
            return "Easy";
        } else if (difficulty == 0.75) {
            return "Medium";
        } else if (difficulty == 1.0) {
            return "Hard";
        }
        return null;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Ogre";
    }
}
