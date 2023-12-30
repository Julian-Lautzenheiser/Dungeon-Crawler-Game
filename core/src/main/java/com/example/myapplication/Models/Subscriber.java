
package com.example.myapplication.Models;

import java.util.ArrayList;
import java.util.List;

public interface Subscriber {
    public List<Enemy> ENEMY_LIST = new ArrayList<Enemy>();
    void addSubscriber(Enemy enemy);
    void removeSubscriber(Enemy enemy);
}
