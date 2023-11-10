package com.example.myapplication.ViewModels;

import com.example.myapplication.Models.DemonEnemy;
import com.example.myapplication.Models.Enemy;
import com.example.myapplication.Models.GoblinEnemy;
import com.example.myapplication.Models.OgreEnemy;
import com.example.myapplication.Models.SkeletonEnemy;

public class EnemyFactory {
    public static Enemy createEnemy(String enemyType) {
        if (enemyType.equalsIgnoreCase("Ogre")) {
            return new OgreEnemy();
        } else if (enemyType.equalsIgnoreCase("Skeleton")) {
            return new SkeletonEnemy();
        } else if (enemyType.equalsIgnoreCase("Goblin")) {
            return new GoblinEnemy();
        } else if (enemyType.equalsIgnoreCase("Demon")) {
            return new DemonEnemy();
        }

        return null; // Return null for unsupported enemy types
    }
}
