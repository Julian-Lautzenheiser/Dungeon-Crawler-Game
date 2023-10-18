package com.example.myapplication.ViewModels;

import com.badlogic.gdx.Input.TextInputListener;
import com.example.myapplication.Models.Player;

public class MyTextInputListener implements TextInputListener {
    private String input = "";
    public boolean exited;
    private Player player = Player.getInstance();

    @Override
    public void input(String text) {
        if (text == null || text == "")
            text = "Larry";
        input = text;
        player.setName(input);
    }

    @Override
    public void canceled() {
        input = "Larry";
    }

    public String getInput() {
        return input;
    }
}
