package com.example.myapplication.ViewModels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.example.myapplication.Models.Enemy;
import com.example.myapplication.Models.Player;
import com.badlogic.gdx.math.Rectangle;

import java.util.List;

public class AttackingViewModel {

    public void checkAttack(List<Enemy> enemyList) {
        Player player = Player.getInstance();
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)
                || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            Gdx.input.setOnscreenKeyboardVisible(false);
            player.setAttacking(true);
            attack(enemyList);
        } else {
            Gdx.input.setOnscreenKeyboardVisible(false);
            player.setAttacking(false);
        }
    }

    public void attack(List<Enemy> enemyList) {
        Player player = Player.getInstance();
        Rectangle enemy = new Rectangle();
        Rectangle weapon = new Rectangle();

        float weaponX = player.getPlayerX() + player.getWidth() / 2;
        float weaponY = player.getPlayerY() + player.getHeight() / 4;
        weapon.set(weaponX, weaponY, 32, 16);

        for (Enemy e: enemyList) {
            enemy.set(e.getPositionX(), e.getPositionY(), e.getWidth(), e.getHeight());
            if (e.getAlive() && enemy.overlaps(weapon)) {
                e.damageTaken();
                e.setScore(player.getDifficulty());
                player.addScore(e.getScore());
                //enemyList.remove(e);
            }
        }
    }

}
