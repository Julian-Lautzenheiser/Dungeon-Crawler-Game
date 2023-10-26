package com.example.myapplication.Models;

public interface Movement {
    abstract void left(int velocity);
    abstract void right(int velocity);
    abstract void down(int velocity);
    abstract void up(int velocity);
}
