package com.example.myapplication.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.example.myapplication.Models.LeaderboardScore;
import com.example.myapplication.ViewModels.Dungeon;
import com.example.myapplication.ViewModels.LeaderboardViewModel;

import java.util.ArrayList;

public class LosingScreen implements Screen {
    private final Dungeon game;
    private OrthographicCamera camera;
    private LeaderboardViewModel leaderboardViewModel;
    private Skin skin;
    private TextButton restart;
    private Stage stage;
    private TextButton.TextButtonStyle style;
    private Skin buttonSkin;
    private Label lost;
    
    public LosingScreen(final Dungeon game) {
        this.game = game;
        leaderboardViewModel = new LeaderboardViewModel();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 20, 15);
        
        String name = game.getPlayerName();
        double score = game.getPlayerScore();
        LeaderboardScore displayScore = new LeaderboardScore(name, score);
        
        String display = displayScore.getName() + " (";
        display += displayScore.getScore() + ") - ";
        display += displayScore.getDatetime();

        // Creates a new skin to display the label text as
        skin = new Skin(Gdx.files.internal("plain-james-ui.json"));
        skin.addRegions(new TextureAtlas(Gdx.files.internal("plain-james-ui.atlas")));
        
        lost = new Label("YOU LOSE!" , skin);
        lost.setFontScale(8, 7);
        Label recent = new Label("New Score: " + display, skin);
        recent.setFontScale(2, 2);
        
        leaderboardViewModel.addScore(displayScore);
        
        Table table = new Table();
        ArrayList<LeaderboardScore> scores = leaderboardViewModel.getTable();
        table.add(lost);
        table.row();
        table.add(recent);
        table.row();
        
        for (LeaderboardScore sc : scores) { //Adds scores in leaderboard to a table to be displayed
            String scoreText = generateLeaderText(sc);
            Label newScore = new Label(scoreText, skin);
            newScore.setFontScale(2, 2);
            table.add(newScore);
            table.row();
        }
        
        createStyle();
        
        restart = new TextButton("Restart", style);
        restart.getLabel().setFontScale(5, 5);
        table.add(restart).width(300);
        
        table.setPosition(1150, 500);
        
        restart.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });
        
        stage.addActor(table);
    }

    public String generateLeaderText(LeaderboardScore ls) {
        String finalText;
        finalText = (ls.getName() + " (" + ls.getScore() + ") - " + ls.getDatetime());
        return finalText;
    }
    
    @Override
    public void show() {
    
    }
    
    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);
        camera.update();
        
        stage.draw();
        stage.act();
    }
    
    @Override
    public void resize(int width, int height) {
    
    }
    
    @Override
    public void pause() {
    
    }
    
    @Override
    public void resume() {
    
    }
    
    @Override
    public void hide() {
    
    }
    
    @Override
    public void dispose() {
    
    }
    
    public void createStyle() {
        //Creates the style to set how the buttons look
        style = new TextButton.TextButtonStyle();
        buttonSkin = new Skin();
        style.font = new BitmapFont();
        style.fontColor = Color.WHITE;
        game.setButtonAtlas(new TextureAtlas(Gdx.files.internal("buttons.atlas")));
        buttonSkin.addRegions(game.getButtonAtlas());
        style.up = buttonSkin.getDrawable("button_up");
        style.down = buttonSkin.getDrawable("button_down");
        style.checked = buttonSkin.getDrawable("button_checked");
    }
}