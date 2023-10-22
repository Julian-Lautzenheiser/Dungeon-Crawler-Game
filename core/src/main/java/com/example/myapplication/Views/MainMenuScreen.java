package com.example.myapplication.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.example.myapplication.ViewModels.Dungeon;

public class MainMenuScreen implements Screen {
private final Dungeon game;
private OrthographicCamera camera;
private TextButton start;
private TextButton quit;
private Skin skin;
private TextButton.TextButtonStyle style;
private Table table;
private Stage stage;

public MainMenuScreen(final Dungeon game) {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        
        style = new TextButton.TextButtonStyle(); //Sets Style for the buttons
        skin = new Skin();
        style.font = new BitmapFont();
        style.fontColor = Color.WHITE;
        game.buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons.atlas"));
        skin.addRegions(game.buttonAtlas);
        style.up = skin.getDrawable("button_up");
        style.down = skin.getDrawable("button_down");
        
        start = new TextButton("Start", style);
        start.getLabel().setFontScale(9, 6);
        quit = new TextButton("Quit", style);
        quit.getLabel().setFontScale(9, 6);
        
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 500, 300);
        
        table = new Table();
        //table.setTransform(true);
        //table.setSize(200, 200);
        table.add(start).width(350).height(200);
        table.row();
        table.add(quit).width(350).height(200);
        
        table.setPosition(1100, 250);
        table.setDebug(true);
        stage.addActor(table);
        addListeners();
    }
    
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        
        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Dungeon Game!!! ", 150, 150);
        game.batch.end();
        
        stage.draw();
        stage.act();
    }
    
    @Override
    public void show() {
    
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
    
    public void addListeners() {
        start.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                game.setScreen(new ConfigScreen(game));
                dispose();
            }
        });
        quit.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                pause();
                dispose();
                Gdx.app.exit();
            }
        });
    }
}
