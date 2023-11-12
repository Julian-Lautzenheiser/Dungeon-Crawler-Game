package com.example.myapplication.Models;

import java.util.ArrayList;
import java.util.List;

public interface Subscriber {
    public List<Enemy> enemyList = new ArrayList<Enemy>();
    void addSubscriber(Enemy E);
    void removeSubscriber(Enemy E);
}
